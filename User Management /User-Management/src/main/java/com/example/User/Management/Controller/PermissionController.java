package com.example.User.Management.Controller;

import com.example.User.Management.DTO.Request.PermissionRequest;
import com.example.User.Management.DTO.Response.MessageResponse;
import com.example.User.Management.DTO.Response.PermissionResponse;
import com.example.User.Management.Service.Imlp.PermissionServiceImlp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permission")

public class PermissionController {
    @Autowired
    private PermissionServiceImlp permissionService;

    @PostMapping("/create")
    public ResponseEntity<PermissionResponse> createPermission(@RequestBody PermissionRequest request){
        return ResponseEntity.ok(permissionService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<PermissionResponse>> getListClasses(){
        List<PermissionResponse> classesList = permissionService.getAll();
        return ResponseEntity.ok(classesList);
    }
    @DeleteMapping("/delete/{permission}")
    public ResponseEntity<?> deleteClasses(@PathVariable String permission){
        permissionService.delete(permission);
        return ResponseEntity.ok( new MessageResponse("Xoa thanh Cong"));
    }
}
