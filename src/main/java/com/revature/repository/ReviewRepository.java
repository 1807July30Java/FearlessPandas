package com.revature.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.Review;

@Repository(value = "reviewRepository")
@Transactional
@EnableTransactionManagement
public class ReviewRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Review> getReviewByReviewerId(int reviewerId){
		Session s = sessionFactory.getCurrentSession();
		Query q = s.getNamedQuery("getReviewByReviewerId");
		q.setInteger("reviewerId", reviewerId);
		return q.list();	
	}
	public List<Review> getReviewByRevieweeId(int revieweeId){
		Session s = sessionFactory.getCurrentSession();
		Query q = s.getNamedQuery("getReviewByRevieweeId");
		q.setInteger("revieweeId", revieweeId);
		return q.list();	
	}

}
