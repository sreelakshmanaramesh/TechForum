package com.techforum.ratingmicroservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.techforum.ratingmicroservice.entity.AnswerRatingData;
import com.techforum.ratingmicroservice.entity.QuestionRatingData;
import com.techforum.ratingmicroservice.model.Answer;
import com.techforum.ratingmicroservice.model.AnswerRatingDto;
import com.techforum.ratingmicroservice.model.PostAnswerRatingDTO;
import com.techforum.ratingmicroservice.model.PostQsnRatingDTO;
import com.techforum.ratingmicroservice.model.Question;
import com.techforum.ratingmicroservice.repository.AnswerRatingRepo;
import com.techforum.ratingmicroservice.repository.QuestionRatingRepo;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	private QuestionRatingRepo questionRatingRepo;
	
	@Autowired
	private AnswerRatingRepo answerRatingRepo;
	
	@Override
	public String createQuestionRatingData(PostQsnRatingDTO postQsnRatingDTO) {
		QuestionRatingData ratingData = new QuestionRatingData(postQsnRatingDTO.getQuestionId(), 0L, 0L);
		questionRatingRepo.save(ratingData);
		return "Question Rating Data Saved";
	}

	@Override
	public String createAnswerRatingData(PostAnswerRatingDTO postAnswerRatingDTO) {
		AnswerRatingData ratingData = new AnswerRatingData(postAnswerRatingDTO.getAnswerId(),postAnswerRatingDTO.getQId(), 0L, 0L);
		answerRatingRepo.save(ratingData);
		return "Answer rating data saved";
	}
	
	public String expressIntrest(Long questionId) {
		QuestionRatingData ratingData = questionRatingRepo.findById(questionId).orElseThrow();
		ratingData.setIntrest(ratingData.getIntrest()+1);
		questionRatingRepo.save(ratingData);
		return "Liked";
	}

	@Override
	public Long getIntrest(Long questionId) {
		return questionRatingRepo.findById(questionId).orElseThrow().getIntrest();
	}
	
	@Override
	public Long getViews(Long questionId) {
		return questionRatingRepo.getById(questionId).getViews();
	}
	
	@Override
	public String incrementViews(Long questionId) {
		QuestionRatingData ratingData = questionRatingRepo.findById(questionId).orElseThrow();
		ratingData.setViews(ratingData.getViews() + 1);
		questionRatingRepo.save(ratingData);
		return "View incremented";
	}

	@Override
	public String upVote(Long answerId) {
		AnswerRatingData ratingData = answerRatingRepo.findById(answerId).orElseThrow();
		ratingData.setUpVotes(ratingData.getUpVotes()+1);
		answerRatingRepo.save(ratingData);
		return "Liked";
	}
	
	public String downVote(Long answerId) {
		AnswerRatingData ratingData = answerRatingRepo.findById(answerId).orElseThrow();
		ratingData.setDownVotes(ratingData.getDownVotes()+1);
		answerRatingRepo.save(ratingData);
		return "Down liked";
	}

	@Override
	public AnswerRatingDto getVotes(Long answerId) {
		AnswerRatingDto dto = new AnswerRatingDto();
		dto.setUpVotes(answerRatingRepo.findById(answerId).orElseThrow().getUpVotes());
		dto.setDownVotes(answerRatingRepo.findById(answerId).orElseThrow().getDownVotes());
		return dto;
	}

	@Override
	public List<Question> sortQuestions() {
		List<QuestionRatingData> qsnList = questionRatingRepo.findAll();
		Collections.sort(qsnList);
		List<Question> sortedQsnList = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		for(QuestionRatingData qData: qsnList) {
			ResponseEntity<Question> qsn = restTemplate.getForEntity("http://localhost:8082/getQuestionById/"+ qData.getQuestionId(), Question.class);
			sortedQsnList.add(qsn.getBody());
		}
		return sortedQsnList;
	}

	@Override
	public List<Answer> sortAnswers(Long qId) {
		List<AnswerRatingData> ansList = answerRatingRepo.findByqId(qId);
		Collections.sort(ansList);
		List<Answer> sortedAnsList = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		for(AnswerRatingData ansData: ansList) {
			ResponseEntity<Answer> qsn = restTemplate.getForEntity("http://localhost:8082/getAnswerById/"+ ansData.getAnswerId(), Answer.class);
			sortedAnsList.add(qsn.getBody());
		}
		return sortedAnsList;
	}

	@Override
	public QuestionRatingData getQuestionRatingData(Long qId) {
		return questionRatingRepo.findById(qId).orElseThrow();
	}
}
