package com.syed.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.syed.blog.dto.UserDto;
import com.syed.blog.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/users")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<UserDto>(service.createUser(userDto), HttpStatus.CREATED);
	}

	@PostMapping("/users/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer id) {
		return new ResponseEntity<UserDto>(service.updateUser(userDto, id), HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
		return new ResponseEntity<UserDto>(service.getUserById(id), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return new ResponseEntity<List<UserDto>>(service.getAllUsers(), HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		service.deleteUser(id);
		return new ResponseEntity<String>("User Successfully deleted", HttpStatus.ACCEPTED);
	}
}
