package com.techforum.qamicroservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.techforum.qamicroservice.entity.Answer;
import com.techforum.qamicroservice.entity.Question;
import com.techforum.qamicroservice.model.User;
import com.techforum.qamicroservice.exception.ResourceNotFoundException;
import com.techforum.qamicroservice.model.EmailDTO;
import com.techforum.qamicroservice.model.UserList;
import com.techforum.qamicroservice.repository.AnswerRepo;
import com.techforum.qamicroservice.repository.QuestionRepo;

import aj.org.objectweb.asm.TypeReference;

@Service
public class AdminQaServiceImpl implements AdminQAService{
	@Autowired
	private QuestionRepo questionRepo;
	
	@Autowired
	private AnswerRepo answerRepo;
	
	@Override
	public List<Question> getUnApprovedQuestions() {
		return questionRepo.findByIsApproved();
	}

	@Override
	public List<Answer> getUnApprovedAnswers() {
		return answerRepo.findByIsApproved();
	}

	@Override
	public Question approveQuestion(Long questionId) {
		Question question = questionRepo.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Question not found"));
		question.setIsApproved(true);
		return questionRepo.save(question);
	}

	@Override
	public Answer approveAnswer(Long answerId) {
		Answer answer = answerRepo.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
		answer.setIsApproved(true);
		Long qid = answer.getQuestion().getId();
		Long userId = questionRepo.findById(qid).get().getAskedByUserId();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User> user= restTemplate.getForEntity("http://localhost:8080/getUserById/" + userId, User.class);
		EmailDTO emailDto = new EmailDTO();
		emailDto.setTo(user.getBody().getEmail());
		emailDto.setSubject("Your question is answered");
		emailDto.setBody("Your question on techforum is answered");
		return answerRepo.save(answer);
	}

	@Override
	public String deleteQuestion(Long questionId) {
		Question question = questionRepo.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Question not found"));
		questionRepo.delete(question);
		return "Question deleted";
	}

	@Override
	public String deleteAnswer(Long answerId) {
	
		Answer answer = answerRepo.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
		answerRepo.delete(answer);
		return "Answer deleted";
	}
	
	@Override
	public User getUserById(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User> user= restTemplate.getForEntity("http://localhost:8080/getUserById/" + id, User.class);
		return user.getBody();
	}

	@Override
	public User getUserByEmail(String email) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User> user= restTemplate.getForEntity("http://localhost:8080/getUserByEmail/" + email, User.class);
		return user.getBody();
	}

	@Override
	public User[] getAllUsers() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User[]> userList= restTemplate.getForEntity("http://localhost:8080/getAllUsers", User[].class);
		return userList.getBody();
	}

	@Override
	public String deleteUserByEmail(String email) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:8080/deleteUserByEmail/" + email);
		return "User deleted";
	}

}
