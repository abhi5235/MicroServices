package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
		UserDTO user = userService.addUserToRepo(userDTO);
		return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
	
	}
	@GetMapping("/user/{userId}")
	public User getUser(@PathVariable int userId) {
		return userService.getUserDataFromDB(userId);
	}
}
