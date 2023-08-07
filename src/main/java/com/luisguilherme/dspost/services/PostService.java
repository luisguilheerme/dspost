package com.luisguilherme.dspost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisguilherme.dspost.dto.PostDTO;
import com.luisguilherme.dspost.models.entities.Post;
import com.luisguilherme.dspost.repositories.PostRepository;
import com.luisguilherme.dspost.services.exceptions.ResourceNotFoundException;

@Service
public class PostService {

	@Autowired
	PostRepository repository;

	public PostDTO findById(String id) {
		Post post = getEntityById(id);
		return new PostDTO(post);
	}

	private Post getEntityById(String id) {
		Optional<Post> result = repository.findById(id);
		return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
	}

	public PostDTO insert(PostDTO dto) {
		Post post = new Post();

		post.setMoment(dto.getMoment());
		post.setTitle(dto.getTitle());
		post.setBody(dto.getBody());
		post.setAuthor(dto.getAuthor());

		post = repository.insert(post);
		return new PostDTO(post);
	}

	public List<PostDTO> findByText(String text) {
		List<Post> list = repository.searchText(text);
		return list.stream().map(x -> new PostDTO(x)).toList();
	}

	public void delete(String id) {
		getEntityById(id);
		repository.deleteById(id);
	}

}
