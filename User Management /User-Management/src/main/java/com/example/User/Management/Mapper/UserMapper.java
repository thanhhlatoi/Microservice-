package com.example.User.Management.Mapper;

import com.example.User.Management.DTO.Request.UserCreateRequest;
import com.example.User.Management.DTO.Request.UserUpdateRequest;
import com.example.User.Management.DTO.Response.UserResponse;
import com.example.User.Management.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
