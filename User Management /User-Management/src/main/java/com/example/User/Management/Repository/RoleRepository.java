package com.example.User.Management.Repository;

import com.example.User.Management.Entity.Role;
import com.example.User.Management.Entity.RoleManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {

}
