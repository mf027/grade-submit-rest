package com.mircea.gradesubmitrest.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mircea.gradesubmitrest.entity.User;
import com.mircea.gradesubmitrest.exception.UserNotFoundException;
import com.mircea.gradesubmitrest.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUser(String userName) {
        return unwrapUser(userRepository.findByUserName(userName));
    }

    @Override
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String userName) {
        userRepository.deleteByUserName(userName);
    }

    public static User unwrapUser(Optional<User> entity) {
        if(entity.isPresent()) return entity.get();
        else throw new UserNotFoundException(User.class);
    }
    
}
