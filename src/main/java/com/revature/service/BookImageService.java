package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.domain.BookImage;
import com.revature.repository.BookImageRepository;
@Service(value = "bookImageService")
public class BookImageService {
	
	@Autowired
	BookImageRepository bookImageRepository;
	
	public List<BookImage> getBookImageByBookId(int bookId){
		return bookImageRepository.getImageByBookId(bookId);
	}
	
	public List<BookImage> getBookImageById(int imageId){
		return bookImageRepository.getImageById(imageId);
	}

}
