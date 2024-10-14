package com.example.demo.controller;

import com.example.demo.model.LoginRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> getAllUsers(HttpServletRequest request) {
		String clientIp = getClientIp(request);
		System.out.println("Client IP: " + clientIp);
		return userService.getAllUsers();
	}

	private String getClientIp(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-Forwarded-For");
		if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
		}
		return ipAddress;
	}

	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestBody User user, HttpServletRequest request) {
		logger.info("Creating user with data: {}", user);
		String clientIpString = getClientIp(request);
		user.setIpString(clientIpString);
//		System.out.println(clientIpString);
		return userService.saveUser(user);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUser(@RequestParam("id") Integer id) {
//    	User deletedUser = userService.deleteUser(user);
//    	return ResponseEntity.ok("deleted");
		return userService.deleteUser(id);
	}

//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
		// TODO
		logger.info("Login attempt with data: {}", loginRequest);
		String clientIpString = getClientIp(request);
		loginRequest.setIpString(clientIpString);
		return userService.loginRequestService(loginRequest);

	}
	
	@GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

	// Additional endpoints can be added as needed
}
