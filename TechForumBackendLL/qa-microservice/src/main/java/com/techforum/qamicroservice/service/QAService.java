package com.techforum.qamicroservice.service;

import java.util.List;

import com.techforum.qamicroservice.entity.Answer;
import com.techforum.qamicroservice.entity.Question;
import com.techforum.qamicroservice.model.AskQuestionDTO;
import com.techforum.qamicroservice.model.PostAnswerDTO;

public interface QAService {
	public Question askQuestion(AskQuestionDTO askQuestionDTO);
	public Answer postAnswer(PostAnswerDTO postAnswerDTO);
	public List<Question> getQuestionsByTopic(String topic);
	public List<Answer> getAnswers(Long questionId);
	public List<Question> searchQuestion(String question);
	public Question getQuestionById(Long qid);
	public Answer getAnswerById(Long aid);
}
