package com.techforum.ratingmicroservice.service;

import java.util.List;

import com.techforum.ratingmicroservice.entity.AnswerRatingData;
import com.techforum.ratingmicroservice.entity.QuestionRatingData;
import com.techforum.ratingmicroservice.model.Answer;
import com.techforum.ratingmicroservice.model.AnswerRatingDto;
import com.techforum.ratingmicroservice.model.PostAnswerRatingDTO;
import com.techforum.ratingmicroservice.model.PostQsnRatingDTO;
import com.techforum.ratingmicroservice.model.Question;

public interface RatingService {
	public String expressIntrest(Long questionId);

	public Long getIntrest(Long questionId);

	public String upVote(Long questionId);
	
	public String downVote(Long questionId);

	public AnswerRatingDto getVotes(Long questionId);

	public Long getViews(Long questionId);

	public String incrementViews(Long questionId);

	public String createQuestionRatingData(PostQsnRatingDTO postQsnRatingDTO);

	public String createAnswerRatingData(PostAnswerRatingDTO postAnswerRatingDTO);

	public List<Question> sortQuestions();

	public List<Answer> sortAnswers(Long qId);

	public QuestionRatingData getQuestionRatingData(Long qId);
}
