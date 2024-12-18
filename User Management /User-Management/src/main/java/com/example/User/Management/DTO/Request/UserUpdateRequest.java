package com.example.User.Management.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
//    private String passWord;
    private String fullName;
    private String email;
    private List<String> roles;
}
