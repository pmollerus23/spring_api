package com.example.demo.service;

import com.example.demo.model.LoginRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	
	private static final String REG_USER_ROLE_STRING = "user";

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
		user.setRole(REG_USER_ROLE_STRING);
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("user created successfully");
	}

	public ResponseEntity<String> deleteUser(Integer id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("deleted user successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with this id");
		}
	}

	public ResponseEntity<String> loginRequestService(LoginRequest loginRequest) {

		Optional<User> userOptional = userRepository.findByEmail(loginRequest.getUsername());
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			if (user.getPassword().equals(loginRequest.getPassword())) {
				System.out.println("log in attempt succeeded for user");
				return ResponseEntity.ok("Login successful"); // You can return a token or user info here
			} else {
				System.out.println("log in attempt failed for username: wrong password");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
			}

		} else {
			System.out.println("username DNE");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}

	}
	// Additional methods can be added as needed
}
