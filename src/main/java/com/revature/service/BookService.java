package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.domain.Book;
import com.revature.repository.BookRepository;

@Service("bookService")
@Scope("Prototype")
public class BookService {
	@Autowired
	BookRepository bookRepository;
	public Book saveBook(Book b) {
		return bookRepository.saveBook(b);
	}
	public Book getBookById(int id) {
		return bookRepository.getBookById(id);
	}
}
