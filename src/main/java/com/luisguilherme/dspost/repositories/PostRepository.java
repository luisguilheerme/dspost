package com.luisguilherme.dspost.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.luisguilherme.dspost.models.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	@Query("{ $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] }")
	List<Post> searchText(String text);
	
	void deleteByAuthorId(String id);
}
