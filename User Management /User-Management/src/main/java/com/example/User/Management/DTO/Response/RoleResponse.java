package com.example.User.Management.DTO.Response;

import com.example.User.Management.Entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    private String name;
    private String description;
    private Set<Permission> permissions;
}
