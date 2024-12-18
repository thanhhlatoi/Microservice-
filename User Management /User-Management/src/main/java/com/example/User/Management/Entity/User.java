package com.example.User.Management.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String passWord;
    private String fullName;
    private String email;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="User_Role",joinColumns = @JoinColumn(name="UserId"),inverseJoinColumns = @JoinColumn(name="roleName"))
    Set<Role> roles;
}
