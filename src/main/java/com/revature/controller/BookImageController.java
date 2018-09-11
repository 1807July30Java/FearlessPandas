package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.BookImage;
import com.revature.service.BookImageService;

@RestController("bookImageController")
@RequestMapping("/image")
public class BookImageController {
	
	@Autowired
	private BookImageService bookImageService;
	@GetMapping("/{bookId}")
	public ResponseEntity<List<BookImage>> getBookImageByBookId(@PathVariable int bookId){
		return new ResponseEntity<>(bookImageService.getBookImageByBookId(bookId), HttpStatus.OK);
	}
	
/*	@GetMapping("/{id}")
	public ResponseEntity<List<BookImage>> getBookImageById(@PathVariable int id){
		return new ResponseEntity<>(bookImageService.getBookImageByBookId(id), HttpStatus.OK);
	}*/

}
