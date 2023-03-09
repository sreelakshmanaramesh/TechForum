package com.techforum.qamicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techforum.qamicroservice.service.AdminQAService;

@RestController
@CrossOrigin("http://localhost:4200")
public class AdminQAController {
	
	@Autowired
	AdminQAService adminService;
	
	@GetMapping("/getUnApprovedQuestions")
	public ResponseEntity<Object> getUnApprovedQuestions() {
		return ResponseEntity.status(HttpStatus.OK).body(adminService.getUnApprovedQuestions());
	}

	@GetMapping("/getUnApprovedAnswers")
	public ResponseEntity<Object> getUnApprovedAnswers() {
		return ResponseEntity.status(HttpStatus.OK).body(adminService.getUnApprovedAnswers());
	}

	@PutMapping("/approveQuestion/{questionId}")
	public ResponseEntity<Object> approveQuestion(@PathVariable Long questionId) {
		return ResponseEntity.status(HttpStatus.OK).body(adminService.approveQuestion(questionId));
	}

	@PutMapping("/approveAnswer/{answerId}")
	public ResponseEntity<Object> approveAnswer(@PathVariable Long answerId) {
		return ResponseEntity.status(HttpStatus.OK).body(adminService.approveAnswer(answerId));
	}

	@DeleteMapping("/deleteQuestion/{questionId}")
	public ResponseEntity<Object> deleteQuestion(@PathVariable Long questionId) {
		return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteQuestion(questionId));
	}

	@DeleteMapping("/deleteAnswer/{answerId}")
	public ResponseEntity<Object> deleteAnswer(@PathVariable Long answerId) {
		return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteAnswer(answerId));
	}

	@GetMapping("/getUser/{email}")
	public ResponseEntity<Object> getUser(@PathVariable String email) {
		return ResponseEntity.status(HttpStatus.OK).body(adminService.getUserByEmail(email));
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<Object> getAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllUsers());
	}
	
	@DeleteMapping("/deleteUser/{email}")
	public ResponseEntity<Object> deleteUserByEmail(@PathVariable String email){
		return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteUserByEmail(email));
	}

}