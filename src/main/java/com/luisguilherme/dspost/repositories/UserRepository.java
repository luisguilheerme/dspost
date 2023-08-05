package com.luisguilherme.dspost.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.luisguilherme.dspost.models.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

}
