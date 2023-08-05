package com.luisguilherme.dspost.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.luisguilherme.dspost.models.entities.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
