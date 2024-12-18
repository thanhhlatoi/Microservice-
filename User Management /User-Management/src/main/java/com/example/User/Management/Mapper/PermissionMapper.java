package com.example.User.Management.Mapper;

import com.example.User.Management.DTO.Request.PermissionRequest;
import com.example.User.Management.DTO.Response.PermissionResponse;
import com.example.User.Management.Entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toUserResponse(Permission permission);
}
