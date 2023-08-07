package com.luisguilherme.dspost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisguilherme.dspost.dto.PostDTO;
import com.luisguilherme.dspost.dto.UserDTO;
import com.luisguilherme.dspost.models.entities.User;
import com.luisguilherme.dspost.repositories.PostRepository;
import com.luisguilherme.dspost.repositories.UserRepository;
import com.luisguilherme.dspost.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	PostRepository postRepository;
	
	public List<UserDTO> findAll() {
		List<User> result = repository.findAll();
		return result.stream().map(x -> new UserDTO(x)).toList();
	}	
	
	public UserDTO findById(String id) {		
		User user = getEntityById(id);
		return new UserDTO(user);
	}
	
	private User getEntityById(String id) {
		Optional<User> result = repository.findById(id);
		return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
	}
	
	public UserDTO insert(UserDTO dto) {
		User user = new User();
		copyDtoToEntity(dto, user);
		user = repository.insert(user);
		return new UserDTO(user);
	}
	
	public void delete(String id) {
		User user = getEntityById(id);		
		repository.deleteById(id);
		postRepository.deleteByAuthorId(user.getId());
	}

	public UserDTO update(String id, UserDTO dto) {
		User user = getEntityById(id);
		copyDtoToEntity(dto, user);
		user = repository.save(user);
		return new UserDTO(user);
	}
	
	public List<PostDTO> getUserPosts(String id) {
		User user = getEntityById(id);
		return user.getPosts().stream().map(x -> new PostDTO(x)).toList();
	}
	
	private void copyDtoToEntity(UserDTO dto, User user) {
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
	}

}
