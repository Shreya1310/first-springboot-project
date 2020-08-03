package com.springboot.firstspringbootproject.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.firstspringbootproject.model.Question;
import com.springboot.firstspringbootproject.service.SurveyService;

@RestController
public class SurveyController {
	
	@Autowired
	private SurveyService surveyService;
	
	//GET
	@GetMapping("/surveys/{surveyId}/questions")
	public List<Question> retrieveQuestionsForSurvey(@PathVariable String surveyId){
		
		return surveyService.retrieveQuestions(surveyId);
		
	}
	
	
	//surveys/{surveyId}/questions
	@PostMapping("/surveys/{surveyId}/questions")
	public ResponseEntity<Object> addQuestionsForSurvey(@PathVariable String surveyId, @RequestBody Question newQuestion){
		//What should be structure of request body?
		/*
		{
			"id": "Question100",
			"description": "Some desc",
			"correctAnswer": "correctAns",
			"options": [
			"Option1",
			"CorrectAns",
			"United States",
			"China"
			]
			}
		*/
		
		//How will it mapped to Question object? ---> Using annotation @RequestBody
		Question question = surveyService.addQuestion(surveyId, newQuestion);
		if(question == null){
			return ResponseEntity.noContent().build();
		}
			
		
		//What should be returned? ---> Success - URI of the new resource in Response Header
		//URI --> surveys/{surveyId}/questions/{questionId} question.getQuestionId()
		

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(question.getId()).toUri();
		
		//What should be response status? ---> Status -- created
        
        return ResponseEntity.created(location).build();

		
		
		
		
	}
	
	//surveys/{surveyId}/questions/{questionId}
	@GetMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveDetailsForQuestion(@PathVariable String surveyId,@PathVariable String questionId){
		
		return surveyService.retrieveQuestion(surveyId, questionId);
	}
	
	
	


}
