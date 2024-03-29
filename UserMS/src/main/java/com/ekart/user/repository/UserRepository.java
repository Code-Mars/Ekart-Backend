package com.ekart.user.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ekart.user.entity.User;


public interface UserRepository extends MongoRepository<User, Long>{
    Optional<User> findByEmail(String email);
    Optional<User> findByMobile(String mobile);
    Optional<User>findByMobileAndEmail(String mobile, String email);
}
