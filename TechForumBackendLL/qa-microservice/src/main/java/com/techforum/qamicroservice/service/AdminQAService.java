package com.techforum.qamicroservice.service;

import java.util.List;

import com.techforum.qamicroservice.entity.Answer;
import com.techforum.qamicroservice.entity.Question;
import com.techforum.qamicroservice.model.User;

public interface AdminQAService {
	public List<Question> getUnApprovedQuestions();

	public List<Answer> getUnApprovedAnswers();

	public Question approveQuestion(Long questionId);

	public Answer approveAnswer(Long answerId);

	public String deleteQuestion(Long questionId);

	public String deleteAnswer(Long answerId);

	public User getUserById(Long id);

	public User getUserByEmail(String email);

	public User[] getAllUsers();

	public String deleteUserByEmail(String email);

}
