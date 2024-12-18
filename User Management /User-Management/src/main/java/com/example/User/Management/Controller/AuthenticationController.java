package com.example.User.Management.Controller;

import com.example.User.Management.DTO.Request.AuthenticationRequest;
import com.example.User.Management.DTO.Request.LogoutRequest;
import com.example.User.Management.DTO.Response.AuthenticationResponse;
import com.example.User.Management.Exception.NotFoundException;
import com.example.User.Management.Service.Imlp.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            var isAuthenticated = authenticationService.authenticate(request);
            // Tạo đối tượng AuthenticationResponse và set giá trị cho nó
            AuthenticationResponse response = new AuthenticationResponse();
//            response.setAuthenticated(isAuthenticated);
            // Trả về đối tượng AuthenticationResponse trong ResponseEntity
            return ResponseEntity.ok(isAuthenticated);
        } catch (NotFoundException ex) {
            // Nếu không tìm thấy user, trả về lỗi với false
            AuthenticationResponse response = new AuthenticationResponse();
            response.setAuthenticated(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception ex) {
            // Nếu có lỗi khác, trả về lỗi với false
            AuthenticationResponse response = new AuthenticationResponse();
            response.setAuthenticated(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/logout")
    private ResponseEntity<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ResponseEntity.ok().build();
    }
}
