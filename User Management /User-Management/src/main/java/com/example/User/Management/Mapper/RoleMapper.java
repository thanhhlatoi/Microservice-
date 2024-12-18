package com.example.User.Management.Mapper;

import com.example.User.Management.DTO.Request.RoleRequest;
import com.example.User.Management.DTO.Request.UserUpdateRequest;
import com.example.User.Management.DTO.Response.RoleResponse;
import com.example.User.Management.Entity.Role;
import com.example.User.Management.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions",ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);

    @Mapping(target = "permissions",ignore = true)
    void updateRole(@MappingTarget Role role, RoleRequest request);
}
