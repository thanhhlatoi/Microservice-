package com.example.User.Management.Repository;

import com.example.User.Management.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,String> {
//    List<Permission> findAllByName(Set<Permission> permissions);
}
