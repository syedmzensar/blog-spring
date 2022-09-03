package com.syed.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syed.blog.dto.UserDto;
import com.syed.blog.entity.User;
import com.syed.blog.exception.ResourceNotFoundException;
import com.syed.blog.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;

	@Autowired
	private ModelMapper modelMapper;

	//Method to create the user
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		User createdUser = this.repo.save(user);
		return modelMapper.map(createdUser, UserDto.class);
	}

	//Method to update the user
	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		User user = this.repo.findById(id).get();

		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());

		User updatedUser = this.repo.save(user);
		return modelMapper.map(updatedUser, UserDto.class);

	}

	//Method to get user by Id
	@Override
	public UserDto getUserById(Integer id) {

		User user = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
		return modelMapper.map(user, UserDto.class);
	}

	//Method to get all users
	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> usersDto = new ArrayList<>();
		List<User> users = repo.findAll();

		for (User u : users) {
			usersDto.add(modelMapper.map(u, UserDto.class));
		}

		return usersDto;
	}

	//Method to get delete user by Id
	@Override
	public void deleteUser(Integer id){
		try {			
			repo.deleteById(id);
		}catch (RuntimeException e) {
			throw new ResourceNotFoundException("Cannot find user with Id " + id + " Unable to delete!!");
		}

	}

}
