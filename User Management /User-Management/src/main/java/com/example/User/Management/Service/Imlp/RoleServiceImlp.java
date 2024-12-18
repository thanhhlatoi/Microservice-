package com.example.User.Management.Service.Imlp;

import com.example.User.Management.DTO.Request.RoleRequest;
import com.example.User.Management.DTO.Response.RoleResponse;
import com.example.User.Management.Entity.Role;
import com.example.User.Management.Mapper.RoleMapper;
import com.example.User.Management.Repository.PermissionRepository;
import com.example.User.Management.Repository.RoleRepository;
import jakarta.transaction.Transactional;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class RoleServiceImlp {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleMapper roleMapper;

    public RoleResponse createRole(RoleRequest request){
        var role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        //lay list cua Role
        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public RoleResponse updateRole(String roleName,RoleRequest request){
        var role = roleRepository.findById(roleName).orElse(null);
           roleMapper.updateRole(role,request);
           //update permission cho role
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    public List<RoleResponse> getAll(){
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }
    @Transactional
    public void delete(String roleName) {
//        // Tìm Role trong cơ sở dữ liệu theo tên
        Role role = roleRepository.findById(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
        // Gỡ bỏ liên kết với Permission
        role.getPermissions().clear();
        // Lưu lại Role sau khi gỡ liên kết
        roleRepository.save(role);
        // Xóa Role
        roleRepository.delete(role);
    }
}
