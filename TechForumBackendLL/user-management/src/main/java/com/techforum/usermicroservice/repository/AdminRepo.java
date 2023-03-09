package com.techforum.usermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techforum.usermicroservice.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Long>{

	public Admin findByEmail(String email);

}
