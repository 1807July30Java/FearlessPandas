package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.domain.Review;
import com.revature.repository.ReviewRepository;

@Service(value = "reviewService")
public class ReviewService {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	public List<Review> getReviewByRevieweeId(int revieweeId){
		return reviewRepository.getReviewByRevieweeId(revieweeId);	
	}
	public List<Review> getReviewByReviewerId(int reviewerId){
		return reviewRepository.getReviewByReviewerId(reviewerId);	
	}
	

}
