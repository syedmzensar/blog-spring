package com.syed.blog.service;

import java.util.List;

import com.syed.blog.dto.PostDto;
import com.syed.blog.entity.Post;
import com.syed.blog.payload.PostResponse;

public interface PostService {

	// create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// update
	PostDto updatePost(PostDto postDto, Integer postId);

	// delete
	void deletePost(Integer postId);

	// get all posts
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize);

	// get posts by id
	PostDto getPostById(Integer postId);

	// get posts by user
	List<PostDto> getPostsByUser(Integer id);

	// get posts by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//find a post with keyword
	List<PostDto> searchPosts(String keyword);

}
