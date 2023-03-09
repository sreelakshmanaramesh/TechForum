package com.techforum.qamicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techforum.qamicroservice.entity.Answer;

public interface AnswerRepo extends JpaRepository<Answer, Long>{
	//@Query("select a from Answer a INNER JOIN a.question q where q.id = ?1")
	@Query("from Answer where question.id = ?1")
	public List<Answer> findAnswersByQuestionId(Long questionId);
	
	@Query("from Answer where isApproved = false")
	public List<Answer> findByIsApproved();
}
