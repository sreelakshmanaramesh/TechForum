package com.techforum.categorymicroservice.service;

import java.util.List;

import com.techforum.categorymicroservice.entity.Category;
import com.techforum.categorymicroservice.model.AskQuestionDTO;
import com.techforum.categorymicroservice.model.Question;

public interface CategoryService {

	public Category createCategory(String categoryName);

	public List<String> getCategoryNames();

	public List<Question> getQuestionsByCategory(String categoryName);

	public Question postQuestionInCategory(String categoryName, AskQuestionDTO askQuestionDTO);

	public List<Question> sortByMostViewed(String categoryName);

}
