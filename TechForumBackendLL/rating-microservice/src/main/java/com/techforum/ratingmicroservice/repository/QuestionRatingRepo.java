package com.techforum.ratingmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techforum.ratingmicroservice.entity.QuestionRatingData;

public interface QuestionRatingRepo extends JpaRepository<QuestionRatingData, Long>{

}
