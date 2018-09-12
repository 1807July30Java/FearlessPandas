package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Bid;
import com.revature.domain.User;
import com.revature.service.BidService;
import com.revature.service.UserService;

@RestController("bidController")
@RequestMapping("/bid")
public class BidController {
	@Autowired
	UserService userService;
	@Autowired
	BidService bidService;
	@PostMapping("/new")
	public ResponseEntity<String> newBid(HttpServletRequest req, @RequestBody Bid b) {
		HttpSession session = req.getSession(false);
		try {
			if(session != null) {
				int id = (int) session.getAttribute("id");
				User u = userService.getUserById(id);
				b.setUser(u);
				bidService.addBidToAuction(b);
				return new ResponseEntity<>("Placed new bid",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("You are not signed in",HttpStatus.OK);
			}	
		}catch(Exception e) {
			return new ResponseEntity<>("Bad Request",HttpStatus.BAD_REQUEST);
		}
		
	}

}
