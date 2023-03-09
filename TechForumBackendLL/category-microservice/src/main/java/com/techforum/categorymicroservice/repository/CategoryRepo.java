package com.techforum.categorymicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techforum.categorymicroservice.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{

	Category findByCategoryName(String categoryName);

}
