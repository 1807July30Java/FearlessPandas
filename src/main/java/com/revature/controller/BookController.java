package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Book;
import com.revature.service.BookService;

@RestController("bookController")
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;
	@GetMapping("/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable int bookId){
		return new ResponseEntity<>(bookService.getBookById(bookId),HttpStatus.OK);
	}
	@GetMapping("/info/{title}/{author}/{publisher}") 
	public ResponseEntity<List<Book>> getBooksByInfo(@PathVariable String title,@PathVariable String author,@PathVariable String publisher){
		System.out.print(title + author + publisher);
		return new ResponseEntity<>(bookService.getBooksByInfo(title, author, publisher),HttpStatus.OK);
	}

}
