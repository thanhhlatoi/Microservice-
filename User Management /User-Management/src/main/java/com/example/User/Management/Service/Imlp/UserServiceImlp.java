package com.example.User.Management.Service.Imlp;

import com.example.User.Management.DTO.Request.UserCreateRequest;
import com.example.User.Management.DTO.Request.UserUpdateRequest;
import com.example.User.Management.DTO.Response.RoleResponse;
import com.example.User.Management.DTO.Response.UserResponse;
import com.example.User.Management.Entity.User;
import com.example.User.Management.Exception.NotFoundException;
import com.example.User.Management.Mapper.UserMapper;
import com.example.User.Management.Repository.RoleRepository;
import com.example.User.Management.Repository.UserRepository;
import com.example.User.Management.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImlp implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User createUser(UserCreateRequest request) {
        User user = userMapper.toUser(request);
        // ma hoa mat khau
        user.setPassWord(passwordEncoder.encode(request.getPassWord()));
        return userRepository.save(user);
    }
    @PreAuthorize("hasAuthority('UPDATE_PROFILE')")
    @Override
    public UserResponse updateUser(long id, UserUpdateRequest request) {
        var user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("k tim that id"));

        userMapper.updateUser(user,request);

        //update roles cho user
        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(long id) {

    }
    //co permission VIEW_PROFILE thi co the dung dc
    @PreAuthorize("hasAuthority('VIEW_PROFILE')")
    @Override
    public UserResponse getMyInFor() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByUserName(name).orElseThrow(() -> new NotFoundException("k tim thay"));

        UserResponse response = userMapper.toUserResponse(user);

        Set<RoleResponse> roles = user.getRoles()
                .stream()
                .map(role -> new RoleResponse(role.getName(),role.getDescription(),role.getPermissions()))
                .collect(Collectors.toSet());
        response.setRoles(roles);
        // Trả về ResponseEntity
        return ResponseEntity.ok(response).getBody();
    }

    @Override
    public List<UserResponse> getAllUser() {
        log.info("In method getAllUser");
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> {
            UserResponse response = userMapper.toUserResponse(user);

            // get role cua uesr
            Set<RoleResponse> roleResponseSet = user.getRoles()
                    .stream()
                    .map(role -> new RoleResponse(role.getName(),role.getDescription(),role.getPermissions()))
                    .collect(Collectors.toSet());
            response.setRoles(roleResponseSet);
            return response;
        }).collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(long id) {
        return null;
    }

}
