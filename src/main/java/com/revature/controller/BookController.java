package com.revature.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Search;
import com.revature.domain.Auction;
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
	
	@GetMapping("/search/{isbn}/{title}/{author}/{publisher}/{minimumPrice}/{endDate}/{buyNow}") 
	public ResponseEntity<List<Auction>> getAuctionBooksByInfo(@PathVariable int isbn, @PathVariable String title,@PathVariable String author,@PathVariable String publisher, @PathVariable int minimumPrice, @PathVariable Date endDate, @PathVariable int buyNow){
		System.out.print(title + author + publisher);
		return new ResponseEntity<>(bookService.getAuctionSearch(isbn,title, author, publisher, minimumPrice, buyNow, endDate),HttpStatus.OK);
	}
	@PostMapping("/searching") 
	public ResponseEntity<List<Auction>> getAuctionBooksByInfo(@RequestBody Search search){		
		System.out.println("************************************************* "+ search);
		return new ResponseEntity<>(bookService.getAuctionSearch(search.getIsbn(),search.getTitle(), search.getAuthor(), search.getPublisher(), search.getMinimumPrice(), search.getBuyItNow(), search.getEndDate()),HttpStatus.OK);
	}
}
