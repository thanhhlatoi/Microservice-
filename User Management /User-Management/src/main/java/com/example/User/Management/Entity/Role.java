package com.example.User.Management.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Role")
public class Role {
    @Id
    private String name;
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="Role_Permission",joinColumns = @JoinColumn(name="roleName"),inverseJoinColumns = @JoinColumn(name="permissionName"))
    Set<Permission> permissions ;


}
