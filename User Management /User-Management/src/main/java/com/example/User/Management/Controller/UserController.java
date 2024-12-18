package com.example.User.Management.Controller;

import com.example.User.Management.DTO.Request.UserCreateRequest;
import com.example.User.Management.DTO.Request.UserUpdateRequest;
import com.example.User.Management.DTO.Response.UserResponse;
import com.example.User.Management.Entity.User;
import com.example.User.Management.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequest request){
        User user = userService.createUser(request);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable long id, @RequestBody UserUpdateRequest request){
        UserResponse response = userService.updateUser(id, request);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/myInFor")
    public ResponseEntity<UserResponse> getGetMyInFor(){
        UserResponse response = userService.getMyInFor();
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
