package com.revature.repository;

import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.Auction;
import com.revature.domain.Bid;

@Repository("bidRepository")
@Transactional
@EnableTransactionManagement
public class BidRepository {
	@Autowired
	SessionFactory sf;
	public Bid addbidToAuction(Bid b) throws Exception {
		Session s = sf.getCurrentSession();
		Auction a = b.getAuction();
		//System.out.println(a);
	    a = (Auction) s.get(Auction.class, a.getAuctionId());
	    Date d = new Date(System.currentTimeMillis());
	    
		if(a != null && a.getEndDate().getTime() > d.getTime()) {
			b.setAuction(a);
			a.getBids().add(b);
			a.setBids(a.getBids());
			s.saveOrUpdate(a);
			s.saveOrUpdate(b);
		}else {
			throw new Exception("No auction with that id");
		}
		//System.out.print(b);
		//System.out.println(a);
		return b;
	}
}