package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.domain.BookCondition;
import com.revature.repository.BookConditionRepository;

@Service(value = "bookConditionService")
public class BookConditionService {
	
	@Autowired
	BookConditionRepository bookConditionRepository;
	
	public BookCondition getBookConditionById(int conditionId) {
		return (BookCondition) bookConditionRepository.getConditionByBookId(conditionId);
	}

}
