package com.techforum.qamicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techforum.qamicroservice.entity.Question;

public interface QuestionRepo extends JpaRepository<Question, Long>{
	@Query("from Question where topic =?1 and isApproved = true")
	public List<Question> findQuestionsByTopicAndApproved(String topic);

	public List<Question> findByQuestionContaining(String question);
	
	@Query("from Question where isApproved = false")
	public List<Question> findByIsApproved();
}
