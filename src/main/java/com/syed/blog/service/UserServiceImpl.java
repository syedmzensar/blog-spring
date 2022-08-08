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
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		User createdUser = repo.save(user);
		return modelMapper.map(createdUser, UserDto.class );
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id){
		User user = this.repo.findById(id).get();

		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		
		User updatedUser = repo.save(user);
		return modelMapper.map(updatedUser, UserDto.class );
		
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = repo.findById(id).get();
		return modelMapper.map(user, UserDto.class );
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> usersDto = new ArrayList<>();
		List<User> users = repo.findAll();
//		users.stream().map(user -> modelMapper(user, UserDto.class))
		
		for(User u: users) {
			usersDto.add(modelMapper.map(u, UserDto.class));
		}
		
		return usersDto;
	}

	@Override
	public void deleteUser(Integer id) {
		repo.deleteById(id);
		
	}

}
