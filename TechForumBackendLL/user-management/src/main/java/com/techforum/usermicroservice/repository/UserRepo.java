package com.techforum.usermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techforum.usermicroservice.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	public User findByEmail(String email);
}
