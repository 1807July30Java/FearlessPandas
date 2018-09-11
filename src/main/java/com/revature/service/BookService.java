package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.domain.Book;
import com.revature.repository.BookRepository;

@Service(value ="bookService")
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public Book getBookById(int bookId) {
		return bookRepository.getBookById(bookId);
	}
}
