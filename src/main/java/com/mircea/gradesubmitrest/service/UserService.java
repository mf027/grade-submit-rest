package com.mircea.gradesubmitrest.service;


import org.springframework.stereotype.Service;

import com.mircea.gradesubmitrest.entity.User;

@Service
public interface UserService {
    
    User getUser(String userName);
    void addUser(User user);
    void deleteUser(String userName);

}
