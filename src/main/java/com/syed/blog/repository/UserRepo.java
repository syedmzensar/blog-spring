package com.syed.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syed.blog.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
