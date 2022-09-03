package com.syed.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syed.blog.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
