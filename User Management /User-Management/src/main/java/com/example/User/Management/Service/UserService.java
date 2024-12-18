package com.example.User.Management.Service;

import com.example.User.Management.DTO.Request.UserCreateRequest;
import com.example.User.Management.DTO.Request.UserUpdateRequest;
import com.example.User.Management.DTO.Response.UserResponse;
import com.example.User.Management.Entity.User;

import java.util.List;

public interface UserService {
    User createUser(UserCreateRequest request);
    UserResponse updateUser(long id, UserUpdateRequest request);
    void deleteUser(long id);
    UserResponse getMyInFor();
    List<UserResponse> getAllUser();
    UserResponse getUserById(long id);

}
