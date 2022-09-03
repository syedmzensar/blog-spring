package com.syed.blog.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {

	private int categoryId;
	
	@NotEmpty(message = "Title should not be empty")
	private String categoryTitle;
	
	@NotEmpty(message = "Give a description")
	private String categoryDescription;
}
