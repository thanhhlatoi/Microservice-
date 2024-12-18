package com.example.User.Management.Service.Imlp;

import com.example.User.Management.DTO.Request.AuthenticationRequest;
import com.example.User.Management.DTO.Request.IntrospectRequest;
import com.example.User.Management.DTO.Request.LogoutRequest;
import com.example.User.Management.DTO.Response.AuthenticationResponse;
import com.example.User.Management.DTO.Response.IntrospectResponse;
import com.example.User.Management.Entity.InvalidatedToken;
import com.example.User.Management.Entity.User;
import com.example.User.Management.Exception.NotFoundException;
import com.example.User.Management.Repository.InvalidatedTokenRepository;
import com.example.User.Management.Repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.nio.file.Paths;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@Slf4j
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InvalidatedTokenRepository invalidatedTokenRepository;

    @Value("${jwt.signerKey:XIUMOY1zMZppH8gTKyXtLu7PJjG5zbI5N8vq28HbQFwO4+Av619325j6k9F3rMOG4pKwH0DIYYmSDorCex882/I3fz+vT4QDoF7YphgGm1J6O34bJJUqi0ZUBJtOzWw8}")
    protected String signerKey;

    public AuthenticationResponse authenticate(AuthenticationRequest request)  {
        var user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new NotFoundException("k tim thay"));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassWord(),
                user.getPassWord());
        if(!authenticated)
            throw new NotFoundException("k tim thay");
        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    // tao token
    private String generateToken(User user){
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("dev.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS)
                                .toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope",buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader,payload);

        try {
            jwsObject.sign(new MACSigner(signerKey));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token",e);
            throw new RuntimeException(e);
        }
    }

    public IntrospectResponse introspect(IntrospectRequest request)
            throws JOSEException, ParseException {
        var token = request.getToken();

         verifyToken(token);

        return IntrospectResponse
                .builder()
                .valid(true)
                .build();

    }

    //Hien thi role cua nguoi dung
    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_"+role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions()
                            .forEach(permission -> stringJoiner.add(permission.getName()));
            });

        return stringJoiner.toString();
    }

    //verifile token
    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        //Kiem tra xem thoi gian het han token chua
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        //Kiem tra xem tra ve false hay true cua token
        var verified = signedJWT.verify(verifier);
        if(!(verified && expiryTime.after(new Date())))
            throw new NotFoundException("k tim thay");
        return signedJWT;
    }

    //logout
    public void logout(LogoutRequest request)throws ParseException, JOSEException {
        var signedToken = verifyToken(request.getToken());

        String jit = signedToken.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedToken.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryDate(expiryTime)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);

    }
}
