package com.syed.blog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {
	
	private Integer id;
	
	@NotEmpty(message = "Name field must not be empty")
	@Size(min = 3, max = 20)
	private String name;
	
	@Email
	@NotEmpty(message ="Email field should not be empty | Malformed email given")
	private String email;
	
	@NotEmpty(message = "Size must be greater than 5")
	@Size(min = 3, max = 20)
	private String password;
	
	@NotEmpty
	private String about;

}
