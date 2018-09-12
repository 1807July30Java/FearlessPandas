package com.revature.service;

import java.util.List;

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
	public List<Book> getBooksByArgs(List<String> args){
		return bookRepository.getBooksByArgs(args);
	}
	public List<Book> getBooksByInfo(String title, String author, String publisher){
		return bookRepository.getBooksByInfo(title,author,publisher);
	}
}
