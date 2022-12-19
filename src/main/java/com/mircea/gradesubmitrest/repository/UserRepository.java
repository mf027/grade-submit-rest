package com.mircea.gradesubmitrest.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mircea.gradesubmitrest.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    @Transactional
    void deleteByUserName(String userName);
    
}
