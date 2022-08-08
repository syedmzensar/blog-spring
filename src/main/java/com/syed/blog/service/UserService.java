package com.syed.blog.service;

import java.util.List;

import com.syed.blog.dto.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer id);

	UserDto getUserById(Integer id);

	List<UserDto> getAllUsers();

	void deleteUser(Integer id);

}
