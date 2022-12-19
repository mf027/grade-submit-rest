package com.mircea.gradesubmitrest.web;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mircea.gradesubmitrest.entity.User;
import com.mircea.gradesubmitrest.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    // @PostMapping("/register")
    // public ResponseEntity<HttpStatus> registerUser(@Valid @RequestBody User user) {
    //     userService.addUser(user);
    //     return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    // }

    // @PostMapping("/login")
    // public ResponseEntity<HttpStatus> loginUser(@Valid @RequestBody User user) {

    // }
    
}
