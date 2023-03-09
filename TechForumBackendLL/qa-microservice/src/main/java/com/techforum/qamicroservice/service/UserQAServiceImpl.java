package com.techforum.qamicroservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.techforum.qamicroservice.entity.Answer;
import com.techforum.qamicroservice.entity.Question;
import com.techforum.qamicroservice.model.User;
import com.techforum.qamicroservice.exception.InvalidCredentialsException;
import com.techforum.qamicroservice.exception.ResourceNotFoundException;
import com.techforum.qamicroservice.model.AskQuestionDTO;
import com.techforum.qamicroservice.model.EmailDTO;
import com.techforum.qamicroservice.model.PostAnswerDTO;
import com.techforum.qamicroservice.model.PostAnswerRatingDTO;
import com.techforum.qamicroservice.repository.AnswerRepo;
import com.techforum.qamicroservice.repository.QuestionRepo;

//import Util.EmailSender;

@Service
public class UserQAServiceImpl implements QAService {

	@Autowired
	private QuestionRepo questionRepo;

	@Autowired
	private AnswerRepo answerRepo;

	@Override
	public Question askQuestion(AskQuestionDTO askQuestionDTO) {
		Question question = new Question();
		question.setTopic(askQuestionDTO.getTopic());
		question.setQuestion(askQuestionDTO.getQuestion());
//		User user = userRepo.findById(askQuestionDTO.getUserId())
//				.orElseThrow(() -> new InvalidCredentialsException("User not Found"));
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User> user = restTemplate
				.getForEntity("http://localhost:8080/getUserById/" + askQuestionDTO.getUserId(), User.class);
		question.setAskedByUserId(askQuestionDTO.getUserId());
		question.setAskedByUser(user.getBody().getName());
		Question savedQuestion = questionRepo.save(question);
		restTemplate.postForEntity("http://localhost:8086/createQuestionRatingData", savedQuestion.getId(), String.class);
		return savedQuestion;
	}

	@Override
	public Answer postAnswer(PostAnswerDTO postAnswerDTO) {
		Answer answer = new Answer();
		Question question = questionRepo.findById(postAnswerDTO.getQuestionId())
				.orElseThrow(() -> new ResourceNotFoundException("Question not found"));
		
		answer.setQuestion(question);
		answer.setAnswer(postAnswerDTO.getAnswer());
//		User answerUser = userRepo.findById(postAnswerDTO.getUserId())
//				.orElseThrow(() -> new InvalidCredentialsException("User not Found"));
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User> user = restTemplate
				.getForEntity("http://localhost:8080/getUserById/" + postAnswerDTO.getUserId(), User.class);
		answer.setAsweredByUserId(postAnswerDTO.getUserId());
		answer.setAnsweredByUser(user.getBody().getName());
//		EmailDTO emailDTO = new EmailDTO();
//		emailDTO.setTo(question.getUser().getEmail());
//		emailDTO.setSubject("Your question on techforum has been answered!");
//		emailDTO.setBody("Question:\n" + question.getQuestion() + "Answer:\n" + answer.getAnswer());
//		EmailSender emailSender = new EmailSender();
//		emailSender.sendMail(emailDTO);
		Answer savedAnswer = answerRepo.save(answer);
		PostAnswerRatingDTO postAnswerRatingDTO = new PostAnswerRatingDTO(savedAnswer.getId(), question.getId());
		restTemplate.postForEntity("http://localhost:8086/createAnswerRatingData", postAnswerRatingDTO, String.class);
		return savedAnswer;
	}
	
	@Override
	public Question getQuestionById(Long qid) {
		return questionRepo.findById(qid).orElseThrow();
	}

	@Override
	public List<Question> getQuestionsByTopic(String topic) {
		List<Question> questions = questionRepo.findQuestionsByTopicAndApproved(topic);
		return questions;
	}

	@Override
	public List<Answer> getAnswers(Long questionId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate
				.postForEntity("http://localhost:8086/incrementView", questionId, String.class);
		List<Answer> answers = answerRepo.findAnswersByQuestionId(questionId);
		return answers;
	}

	@Override
	public List<Question> searchQuestion(String question) {
		List<Question> questions = questionRepo.findByQuestionContaining(question);
		return questions;
	}
	
	@Override
	public Answer getAnswerById(Long aid) {
		return answerRepo.findById(aid).orElseThrow();
	}

}
