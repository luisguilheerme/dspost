package com.luisguilherme.dspost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.luisguilherme.dspost.dto.UserDTO;
import com.luisguilherme.dspost.models.entities.User;
import com.luisguilherme.dspost.repositories.UserRepository;
import com.luisguilherme.dspost.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public List<UserDTO> findAll() {
		List<User> result = repository.findAll();
		return result.stream().map(x -> new UserDTO(x)).toList();
	}	
	
	public UserDTO findById(String id) {		
		User user = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso nao encontrado"));
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
		getEntityById(id);
		repository.deleteById(id);
	}

	public UserDTO update(String id, UserDTO dto) {
		User user = getEntityById(id);
		copyDtoToEntity(dto, user);
		user = repository.save(user);
		return new UserDTO(user);
	}
	
	private void copyDtoToEntity(UserDTO dto, User user) {
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
	}

}
