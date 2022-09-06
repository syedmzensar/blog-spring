package com.syed.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.syed.blog.dto.PostDto;
import com.syed.blog.payload.PostResponse;
import com.syed.blog.service.PostService;

@RestController
public class PostController {

	@Autowired
	private PostService postService;

	// POST - Create post
	@PostMapping("/post/{userId}/{categoryId}")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		return new ResponseEntity<PostDto>(this.postService.createPost(postDto, userId, categoryId),
				HttpStatus.CREATED);
	}

	// PUT - Update post
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId) {
		return new ResponseEntity<PostDto>(this.postService.updatePost(postDto, postId), HttpStatus.OK);
	}

	// DELETE - Delete post
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<String>("Post deleted sucessfully", HttpStatus.OK);
	}

	// GET - get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(

			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize

	) {
		return new ResponseEntity<PostResponse>(this.postService.getAllPosts(pageNumber, pageSize), HttpStatus.OK);
	}

	// GET - get post by id
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		return new ResponseEntity<PostDto>(this.postService.getPostById(postId), HttpStatus.OK);
	}

	// GET - get post by user id
	@GetMapping("/post/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId") Integer id) {
		return new ResponseEntity<List<PostDto>>(this.postService.getPostsByUser(id), HttpStatus.OK);
	}

	// GET - get post by category id
	@GetMapping("/post/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
		return new ResponseEntity<List<PostDto>>(this.postService.getPostsByCategory(categoryId), HttpStatus.OK);
	}

	// GET - search post by title
	@GetMapping("/post/title/{keyword}")
	public ResponseEntity<List<PostDto>> searchPosts(@PathVariable String keyword) {
		return new ResponseEntity<List<PostDto>>(this.postService.searchPosts(keyword), HttpStatus.OK);
	}

}
