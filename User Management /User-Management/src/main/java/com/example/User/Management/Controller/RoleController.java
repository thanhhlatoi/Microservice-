package com.example.User.Management.Controller;

import com.example.User.Management.DTO.Request.RoleRequest;
import com.example.User.Management.DTO.Response.MessageResponse;
import com.example.User.Management.DTO.Response.RoleResponse;
import com.example.User.Management.Service.Imlp.RoleServiceImlp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    public RoleServiceImlp roleService;

    @PostMapping("/create")
    public ResponseEntity<RoleResponse> createRole(@RequestBody RoleRequest request){
        return ResponseEntity.ok(roleService.createRole(request));
    }
    @PutMapping("/update/{roleName}")
    public ResponseEntity<RoleResponse> updateRole(@PathVariable String roleName,@RequestBody RoleRequest request){
        return ResponseEntity.ok(roleService.updateRole(roleName,request));
    }
    @GetMapping
    public ResponseEntity<List<RoleResponse>> getListClasses(){
        List<RoleResponse> classesList = roleService.getAll();
        return ResponseEntity.ok(classesList);
    }
    @DeleteMapping("/delete/{roleName}")
    public ResponseEntity<?> deleteClasses(@PathVariable String roleName){
        roleService.delete(roleName);
        return ResponseEntity.ok( new MessageResponse("Xoa thanh Cong"));
    }
}
