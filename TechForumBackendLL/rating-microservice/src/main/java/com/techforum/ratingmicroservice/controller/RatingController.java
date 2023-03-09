package com.techforum.ratingmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techforum.ratingmicroservice.model.PostAnswerRatingDTO;
import com.techforum.ratingmicroservice.model.PostQsnRatingDTO;
import com.techforum.ratingmicroservice.service.RatingService;

@RestController
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/createQuestionRatingData")
	public ResponseEntity<Object> createQuestionRatingData(@RequestBody PostQsnRatingDTO postQsnRatingDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.createQuestionRatingData(postQsnRatingDTO));
	}
	
	@GetMapping("/getQuestionRatingData/{qId}")
	public ResponseEntity<Object> getQuestionRatingData(@PathVariable Long qId) {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getQuestionRatingData(qId));
	}
	
	@PostMapping("/createAnswerRatingData")
	public ResponseEntity<Object> createAnswerRatingData(@RequestBody PostAnswerRatingDTO postAnswerRatingDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.createAnswerRatingData(postAnswerRatingDTO));
	}
	
	@PostMapping("/expressIntrest")
	public ResponseEntity<Object> expressIntrest(@RequestBody Long questionId) {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.expressIntrest(questionId));
	}
	
	@GetMapping("/getViews")
	public ResponseEntity<Object> getViews(@PathVariable Long questionId) {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getViews(questionId));
	}
	
	@PostMapping("/incrementView")
	public ResponseEntity<Object> incrementViews(@RequestBody Long questionId) {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.incrementViews(questionId));
	}
	
	@GetMapping("/getIntrest")
	public ResponseEntity<Object> getIntrest(@PathVariable Long questionId) {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getIntrest(questionId));
	}
	
	@PostMapping("/upVote")
	public ResponseEntity<Object> upVote(@RequestBody Long answerId) {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.upVote(answerId));
	}
	
	@PostMapping("/downVote")
	public ResponseEntity<Object> downVote(@RequestBody Long answerId) {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.downVote(answerId));
	}
	
	@GetMapping("/getVotes/{answerId}")
	public ResponseEntity<Object> getVotes(@PathVariable Long answerId) {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getVotes(answerId));
	}
	
	@GetMapping("/sortQuestionsByViews")
	public ResponseEntity<Object> sortQuestions() {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.sortQuestions());
	}
	
	@GetMapping("/sortAnswersByUpvotes/{qId}")
	public ResponseEntity<Object> sortAnswers(@PathVariable Long qId) {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.sortAnswers(qId));
	}
		
}
