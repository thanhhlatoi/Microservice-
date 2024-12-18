package com.example.User.Management.Service.Imlp;

import com.example.User.Management.DTO.Request.PermissionRequest;
import com.example.User.Management.DTO.Response.PermissionResponse;
import com.example.User.Management.Entity.Permission;
import com.example.User.Management.Mapper.PermissionMapper;
import com.example.User.Management.Repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImlp {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PermissionMapper permissionMapper;


    public PermissionResponse create(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toUserResponse(permission);
    }

    public List<PermissionResponse> getAll(){
        var permission = permissionRepository.findAll();
        return permission.stream().map(permissionMapper::toUserResponse).toList();
    }

    public void delete(String permission ){
//        Permission permission = permissionRepository.findAllById(permission).orElseThrow(() -> new NotFoundException("k tim thay id"));
        permissionRepository.deleteById(permission);
    }
}
