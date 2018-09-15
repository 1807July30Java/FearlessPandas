package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Auction;
import com.revature.domain.User;
import com.revature.service.AuctionService;
import com.revature.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("auctionController")
@RequestMapping("/auction")
public class AuctionController {
	@Autowired
	AuctionService auctionService;
	@Autowired
	UserService userService;
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Auction> getAuctionById(@PathVariable int id){
		return new ResponseEntity<>(auctionService.getAuctionById(id),HttpStatus.OK);
	}
	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Auction>> getAllAuctions(){
		return new ResponseEntity<>(auctionService.getAllAuctions(),HttpStatus.OK);
	}
	@GetMapping("/closed/{id}")
	@ResponseBody
	public ResponseEntity<Boolean> isClosed(@PathVariable int id){
		return new ResponseEntity<>(auctionService.isClosed(id),HttpStatus.OK);
	}
	@GetMapping("/this")
	@ResponseBody
	public ResponseEntity<List<Auction>> getAllUserAuctions(HttpServletRequest req) {
		return new ResponseEntity<>(auctionService.getAllUserAuctions((int)req.getSession(false).getAttribute("id")),HttpStatus.OK);
	}
	
	@PostMapping("/new")
	@ResponseBody
	public ResponseEntity<String> newAuction(HttpServletRequest req,HttpServletResponse res,@RequestBody Auction A){
		
		try {
			int id = (int) req.getSession().getAttribute("id");
			User u = userService.getUserById(req, id);
			A.setUser(u);
			auctionService.saveAuction(A);
			return new ResponseEntity<>("Successfully created new Auction",HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to create new Auction",HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/new/book")
	@ResponseBody
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
