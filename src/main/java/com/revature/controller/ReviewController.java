package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Review;
import com.revature.service.ReviewService;

@RestController("reviewController")
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping(value="/{revieweeId}")
	public ResponseEntity<List<Review>> getReviewByUserId(@PathVariable int revieweeId){
		return new ResponseEntity<List<Review>>(reviewService.getReviewByRevieweeId(revieweeId),HttpStatus.OK);
	}
	@GetMapping(value="/reviewer/{reviewerId}")
	public ResponseEntity<List<Review>> getReviewByReviewerId(@PathVariable int reviewerId){
		return new ResponseEntity<List<Review>>(reviewService.getReviewByReviewerId(reviewerId),HttpStatus.OK);
	}

}
