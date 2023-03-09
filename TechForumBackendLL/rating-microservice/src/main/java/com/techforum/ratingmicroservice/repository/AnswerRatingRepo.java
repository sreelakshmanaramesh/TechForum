package com.techforum.ratingmicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techforum.ratingmicroservice.entity.AnswerRatingData;

public interface AnswerRatingRepo extends JpaRepository<AnswerRatingData, Long>{
	public List<AnswerRatingData> findByqId(Long Id); 
}
