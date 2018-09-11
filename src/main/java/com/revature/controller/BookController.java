package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Book;
import com.revature.service.BookService;

@RestController("bookController")
@RequestMapping("/book")
public class BookController {
	@Autowired
	BookService bookService;
	
	@GetMapping("/new")
	public ResponseEntity<String> newBook(Book b){
		try {
			bookService.saveBook(b);
			return new ResponseEntity<>("Successfully saved new book", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to save new book",HttpStatus.BAD_REQUEST);
		}
	}
}
