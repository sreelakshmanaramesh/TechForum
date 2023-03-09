package com.techforum.qamicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techforum.qamicroservice.entity.Answer;
import com.techforum.qamicroservice.entity.Question;
import com.techforum.qamicroservice.model.AskQuestionDTO;
import com.techforum.qamicroservice.model.PostAnswerDTO;
import com.techforum.qamicroservice.service.UserQAServiceImpl;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserQAController {
	
	@Autowired
	private UserQAServiceImpl qaService;
	
	@PostMapping("/postQuestion")
	public ResponseEntity<Object> askQuestion(@RequestBody AskQuestionDTO askQuestionDTO) {
		Question askedQuestion = qaService.askQuestion(askQuestionDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(askedQuestion);
	}

	@PostMapping("/postAnswer")
	public ResponseEntity<Object> giveAnswer(@RequestBody PostAnswerDTO postAnswerDTO) {
		Answer postedAnswer = qaService.postAnswer(postAnswerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(postedAnswer);
	}
	
	@GetMapping("/getQuestionById/{qid}")
	public ResponseEntity<Object> getQuestionById(@PathVariable Long qid){
		Question question = qaService.getQuestionById(qid);
		return ResponseEntity.status(HttpStatus.OK).body(question);
	}
	
	@GetMapping("/getAnswerById/{aid}")
	public ResponseEntity<Object> getAnswerById(@PathVariable Long aid){
		Answer answer = qaService.getAnswerById(aid);
		return ResponseEntity.status(HttpStatus.OK).body(answer);
	}
	
//	@GetMapping("/getQuestions/{topic}")
//	public ResponseEntity<Object> getQuestionsByTopic(@PathVariable String topic){
//		List<Question> questions = qaService.getQuestionsByTopic(topic);
//		return ResponseEntity.status(HttpStatus.OK).body(questions);
//	}
	
	@GetMapping("/getAnswers/{questionId}")
	public ResponseEntity<Object> getAnswers(@PathVariable Long questionId){
		List<Answer> answers = qaService.getAnswers(questionId);
		return ResponseEntity.status(HttpStatus.OK).body(answers);
	}
	
	@GetMapping("/searchQuestionByKeyword/{question}")
	public ResponseEntity<Object> searchQuestion(@PathVariable String question){
		List<Question> questions = qaService.searchQuestion(question);
		return ResponseEntity.status(HttpStatus.OK).body(questions);
	}
}
