package com.revature.repository;

import com.revature.domain.Auction;
import com.revature.domain.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
@Repository(value = "userRepository")
@Transactional
@EnableTransactionManagement
public class AuctionRepository {
	@Autowired
	SessionFactory sessionFactory;
	public Auction saveAuction(Auction a) {
		Session s = sessionFactory.getCurrentSession();
        s.save(a);
        return a;
	}
	public Auction getAuctionById(int id) {
		Session s = sessionFactory.getCurrentSession();
		return (Auction) s.get(Auction.class, id);
	}
}
