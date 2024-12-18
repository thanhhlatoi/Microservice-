package com.example.User.Management.Config;

import com.example.User.Management.DTO.Response.UserResponse;
import com.example.User.Management.Entity.Role;
import com.example.User.Management.Entity.RoleManager;
import com.example.User.Management.Entity.User;
import com.example.User.Management.Repository.RoleRepository;
import com.example.User.Management.Repository.UserRepository;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
public class ApplicationInitConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

//    ApplicationRunner applicationRunner(UserRepository userRepository){
//        return args -> {
//            // Đảm bảo tất cả các Role trong RoleManager đều tồn tại trong DB
//
//            if (userRepository.findByUserName("admin").isEmpty()){
//                var roles = new HashSet<Role>();
//                roles.add(RoleManager.ADMIN.name());
////                Set<Role> roles = new HashSet<>();
////                roles.add();
//                User user = User.builder()
//                        .userName("admin")
//                        .passWord(passwordEncoder.encode("admin"))
//                        .roles(roles)
//                        .build();
//                userRepository.save(user);
//                log.warn("admin user has been created with default password: admin, please change it");
//            }
//
//        };
//    }

}
