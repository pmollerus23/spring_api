package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

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
		String clientIpString = getClientIp(request);
		user.setIpString(clientIpString);
        return userService.saveUser(user);
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam("id") Long id) {
//    	User deletedUser = userService.deleteUser(user);
//    	return ResponseEntity.ok("deleted");
    	return userService.deleteUser(id);
    }
    // Additional endpoints can be added as needed
}
