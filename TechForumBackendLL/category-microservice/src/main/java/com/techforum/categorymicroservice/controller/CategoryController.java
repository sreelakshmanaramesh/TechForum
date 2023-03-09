package com.techforum.categorymicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techforum.categorymicroservice.model.AskQuestionDTO;
import com.techforum.categorymicroservice.service.CategoryService;

@RestController
@CrossOrigin("http://localhost:4200")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/createCategory/{categoryName}")
	public ResponseEntity<Object> createCategory(@PathVariable String categoryName){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryName));
	}
	
	@GetMapping("/getCategoryNames")
	public ResponseEntity<Object> getCategories(@PathVariable String categoryName){
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryNames());
	}
	
	@GetMapping("/getQuestionsByCategory/{categoryName}")
	public ResponseEntity<Object> getQuestionsByCategory(@PathVariable String categoryName){
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.getQuestionsByCategory(categoryName));
	}
	
	@PostMapping("/postQuestionByCategory/{categoryName}")
	public ResponseEntity<Object> createCategory(@PathVariable String categoryName, @RequestBody AskQuestionDTO askQuestionDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.postQuestionInCategory(categoryName, askQuestionDTO));
	}
	
	@GetMapping("/sortByMostViewed/{categoryName}")
	public ResponseEntity<Object> sortByMostViewed(@PathVariable String categoryName){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.sortByMostViewed(categoryName));
	}
	
}
