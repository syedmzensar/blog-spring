package com.syed.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syed.blog.entity.Category;
import com.syed.blog.entity.Post;
import com.syed.blog.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category cat);
}
