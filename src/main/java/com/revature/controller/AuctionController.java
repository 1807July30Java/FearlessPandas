package com.revature.controller;

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

import com.revature.domain.Auction;
import com.revature.service.AuctionService;

@RestController("auctionController")
@RequestMapping("/auction")
public class AuctionController {
	@Autowired
	AuctionService auctionService;
	@GetMapping("/{id}")
	public ResponseEntity<Auction> getAuctionById(@PathVariable int id){
		return new ResponseEntity<>(auctionService.getAuctionById(id),HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<List<Auction>> getAllAuctions(){
		return new ResponseEntity<>(auctionService.getAllAuctions(),HttpStatus.OK);
	}
	@GetMapping("/closed/{id}")
	public ResponseEntity<Boolean> isClosed(@PathVariable int id){
		return new ResponseEntity<>(auctionService.isClosed(id),HttpStatus.OK);
	}
	@PostMapping("/new")
	public ResponseEntity<String> newAuction(@RequestBody Auction A){
		System.out.println(A.getCreateDate());
		try {
			auctionService.saveAuction(A);
			return new ResponseEntity<>("Successfully created new Auction",HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to create new Auction", HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/new/book")
	public ResponseEntity<String> newAuctionOldBook(@RequestBody Auction A){
		System.out.println(A.getCreateDate());
		try {
			auctionService.saveAuctionWithBook(A);
			return new ResponseEntity<>("Successfully created new Auction",HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to create new Auction", HttpStatus.BAD_REQUEST);
		}
	}
}
