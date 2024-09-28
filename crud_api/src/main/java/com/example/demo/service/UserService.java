package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<String> saveUser(User user) {
    	String email = user.getEmail();
    	if (userRepository.existsByEmail(email)) {
    		return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists");
    	}
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("user created successfully");
    }

	public ResponseEntity<String> deleteUser(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("deleted user successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with this id");
		}
	}

    // Additional methods can be added as needed
}
