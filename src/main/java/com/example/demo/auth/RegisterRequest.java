package com.example.demo.auth;

import com.example.demo.entity.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {



    public int age;
    private String nom;
    private String email;
    private String username;
    private String password ;
    private Role role;
    private String phoneNumber;
    private Date dateNaissance;
    private String Skills ;
    private String availability ;



}
