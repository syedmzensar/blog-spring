package com.syed.blog.service;

import java.util.List;

import com.syed.blog.dto.CategoryDto;

public interface CategoryService {

	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	void deleteCategory(Integer categoryId);
	
	//get by id
	CategoryDto getCategoryById(Integer categoryId);
	
	//get all
	List<CategoryDto> getAllCategories();
}
