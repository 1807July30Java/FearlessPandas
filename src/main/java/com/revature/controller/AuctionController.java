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
import com.revature.domain.Bid;
import com.revature.domain.User;
import com.revature.service.AuctionService;
import com.revature.service.BidService;
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
	@Autowired
	BidService bidService;
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Auction> getAuctionById(HttpServletRequest req,@PathVariable int id){
		int reqid;
		try {
			reqid = (int) req.getSession().getAttribute("id");
		}catch(Exception e) {
			return null;
		}
		return new ResponseEntity<>(auctionService.getAuctionById(id,reqid),HttpStatus.OK);
	}
	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Auction>> getAllAuctions(HttpServletRequest req){
		int id;
		try {
			id = (int) req.getSession().getAttribute("id");
		}catch(Exception e) {
			return null;
		}
		return new ResponseEntity<>(auctionService.getAllAuctions(id),HttpStatus.OK);
	}
	@GetMapping("/closed/{id}")
	@ResponseBody
	public ResponseEntity<Integer> isClosed(@PathVariable int id){
		return new ResponseEntity<>(auctionService.isClosed(id),HttpStatus.OK);
	}
	@GetMapping("/this")
	@ResponseBody
	public ResponseEntity<List<Auction>> getAllUserAuctions(HttpServletRequest req) {
		int id;
		try {
			id = (int) req.getSession().getAttribute("id");
		}catch(Exception e) {
			return null;
		}
		return new ResponseEntity<>(auctionService.getAllUserAuctions(id),HttpStatus.OK);
	}
	@GetMapping("/closed")
	@ResponseBody
	public ResponseEntity<List<Auction>> getClosedAuctions(HttpServletRequest req){
		int id;
		try {
			id = (int) req.getSession().getAttribute("id");
		}catch(Exception e) {
			return null;
		}
		return new ResponseEntity<>(auctionService.getClosedAuctions(id),HttpStatus.OK);
	}
	@GetMapping("/bids/{auctionid}")
	public ResponseEntity<List<Bid>> getBidsByAuction(HttpServletRequest req,@PathVariable int auctionid){
		Auction a = auctionService.getAuctionById(auctionid, (int)req.getSession(false).getAttribute("id"));
		return new ResponseEntity<>(bidService.getBidsByAuction(a),HttpStatus.OK);
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
