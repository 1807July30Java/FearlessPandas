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
	@GetMapping("/{imageId}")
	public ResponseEntity<BookImage> getBookImageById(@PathVariable int imageId){
		return new ResponseEntity<>(bookImageService.getBookImageById(imageId), HttpStatus.OK);
	}
	@GetMapping("/book/{id}")
	public ResponseEntity<List<BookImage>> getBookImageByBookId(@PathVariable int id){
		return new ResponseEntity<>(bookImageService.getBookImageByBookId(id),HttpStatus.OK);
	}
	

}
