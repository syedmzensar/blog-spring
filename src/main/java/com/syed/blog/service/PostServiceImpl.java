package com.syed.blog.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.syed.blog.dto.PostDto;
import com.syed.blog.entity.Category;
import com.syed.blog.entity.Post;
import com.syed.blog.entity.User;
import com.syed.blog.exception.ResourceNotFoundException;
import com.syed.blog.payload.PostResponse;
import com.syed.blog.repository.CategoryRepo;
import com.syed.blog.repository.PostRepo;
import com.syed.blog.repository.UserRepo;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));

		Post post = this.modelMapper.map(postDto, Post.class);

		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post savedPost = this.postRepo.save(post);

		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).get();

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());

		Post savedPost = this.postRepo.save(post);

		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		this.postRepo.deleteById(postId);

	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize) {
		Pageable p = PageRequest.of(pageNumber, pageSize);

		Page<Post> pagePosts = this.postRepo.findAll(p);

		List<Post> posts = pagePosts.getContent();

		List<PostDto> allPosts = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		new PostResponse().setContent(allPosts);
		new PostResponse().setPageNumber(pagePosts.getNumber());
		new PostResponse().setPageSize(pagePosts.getSize());
		new PostResponse().setTotalElements(pagePosts.getTotalElements());
		new PostResponse().setTotalPages(pagePosts.getTotalPages());
		new PostResponse().setLastPage(pagePosts.isLast());

		return new PostResponse();
	}

	@Override
	public PostDto getPostById(Integer postId) {

		Post post = this.postRepo.findById(postId).get();

		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByUser(Integer id) {
		User user = this.userRepo.findById(id).get();
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> allPosts = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return allPosts;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).get();
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> allPosts = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return allPosts;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> allPosts = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return allPosts;
	}

}
