package com.example.Profile.Microservice.Entity;

import jakarta.persistence.*;
import lombok.*;

import javax.annotation.processing.Generated;
import java.sql.Date;

@Entity
@Table(name = "UserProfile")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
    private String address;
    private boolean gender;

}
