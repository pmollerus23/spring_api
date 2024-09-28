package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
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
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
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
