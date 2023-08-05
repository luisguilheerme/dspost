package com.luisguilherme.dspost.models.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.luisguilherme.dspost.models.embedded.Author;
import com.luisguilherme.dspost.models.embedded.Comment;

@Document(collection = "posts")
public class Post {

	@Id
	private String id;
	private Instant moment;
	private String title;
	private String body;

	private Author author;
	
	private List<Comment> comments = new ArrayList<>();
	
	public Post() {

	}

	public Post(String id, Instant moment, String title, String body) {
		this.id = id;
		this.moment = moment;
		this.title = title;
		this.body = body;
	}
	
	public Post(Post entity) {
		id = entity.getId();
		moment = entity.getMoment();
		title = entity.getTitle();
		body = entity.getBody();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
