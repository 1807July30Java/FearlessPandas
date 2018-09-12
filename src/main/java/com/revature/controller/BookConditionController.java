package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.BookCondition;
import com.revature.service.BookConditionService;

@RestController("bookConditionController")
@RequestMapping("/bookCondition")
public class BookConditionController {

	@Autowired
	private BookConditionService bookConditionService;
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<BookCondition> getBookCondition(@PathVariable int id){
		return new ResponseEntity<>(bookConditionService.getBookConditionById(id),HttpStatus.OK);
	}
	
	
}
