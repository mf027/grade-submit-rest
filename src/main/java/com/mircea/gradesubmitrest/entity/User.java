package com.mircea.gradesubmitrest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name")
    @NotBlank(message = "Username cannot be blank!")
    private String userName;
    @NotBlank(message = "Password cannot be blank!")
    @Column(name = "password")
    private String password;
    @NotBlank(message = "Please define a role for the user")
    @Column(name = "user_role")
    private String role;
    
}
