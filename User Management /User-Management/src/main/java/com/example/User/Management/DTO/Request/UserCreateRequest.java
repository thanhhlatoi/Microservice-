package com.example.User.Management.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {
    private String userName;
    private String passWord;
    private String fullName;
}
