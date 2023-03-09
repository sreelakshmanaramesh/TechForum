package com.techforum.categorymicroservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.techforum.categorymicroservice.entity.Category;
import com.techforum.categorymicroservice.model.AskQuestionDTO;
import com.techforum.categorymicroservice.model.Question;
import com.techforum.categorymicroservice.model.QuestionRatingData;
import com.techforum.categorymicroservice.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Category createCategory(String categoryName) {
		Category category = new Category();
		category.setCategoryName(categoryName);
		return categoryRepo.save(category);
	}

	@Override
	public List<String> getCategoryNames() {
		List<String> categoryNameList = new ArrayList<>();
		List<Category> categoryList = categoryRepo.findAll();
		for(Category category: categoryList) {
			categoryNameList.add(category.getCategoryName());
		}
		return categoryNameList;
	}

	@Override
	public List<Question> getQuestionsByCategory(String categoryName) {
		RestTemplate restTemplate = new RestTemplate();
		Category category = categoryRepo.findByCategoryName(categoryName);
		List<Question> questionList = new ArrayList<>();
		for(Long qid: category.getQuestionIds()) {
			ResponseEntity<Question> question= restTemplate.getForEntity("http://localhost:8082/getQuestionById/" + qid, Question.class);
			System.out.println(question.toString());
			questionList.add(question.getBody());
		}
		return questionList;
	}

	@Override
	public Question postQuestionInCategory(String categoryName, AskQuestionDTO askQuestionDTO) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Question> response = restTemplate.postForEntity("http://localhost:8082/postQuestion", askQuestionDTO, Question.class);
		List<Long> qids = categoryRepo.findByCategoryName(categoryName).getQuestionIds();
		qids.add(response.getBody().getId());
		Category cat = categoryRepo.findByCategoryName(categoryName);
		cat.setQuestionIds(qids);
		categoryRepo.save(cat);
		return response.getBody();
	}

	@Override
	public List<Question> sortByMostViewed(String categoryName) {
		List<QuestionRatingData> questionRatingDataList = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		for(Long qId: categoryRepo.findByCategoryName(categoryName).getQuestionIds()) {
			ResponseEntity<QuestionRatingData> response = restTemplate.getForEntity("http://localhost:8086/getQuestionRatingData/" + qId, QuestionRatingData.class);
			questionRatingDataList.add(response.getBody());
		}
		Collections.sort(questionRatingDataList);
		List<Question> questions = new ArrayList<>();
		for(QuestionRatingData q: questionRatingDataList) {
			ResponseEntity<Question> response = restTemplate.getForEntity("http://localhost:8082/getQuestionById/" + q.getQuestionId(), Question.class);
			questions.add(response.getBody());
		}
		return questions;
	}
	
	
}
