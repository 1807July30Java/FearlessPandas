package com.revature.repository;

import com.revature.domain.Auction;
import com.revature.domain.Book;
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
import java.sql.Date;
import java.util.ArrayList;
@Repository(value = "auctionRepository")
@Transactional
@EnableTransactionManagement
public class AuctionRepository {
	@Autowired
	SessionFactory sessionFactory;
	public Auction saveAuction(Auction a) {
		Session s = sessionFactory.getCurrentSession();
        s.saveOrUpdate(a);
        return a;
	}
	public List<Auction> getAllAuctions(){
		Session s = sessionFactory.getCurrentSession();
		Query q = s.getNamedQuery("getAllAuctions");
		return q.list();
	}
	public List<Auction> getAllAuctionsBefore(Date d){
		Session s = sessionFactory.getCurrentSession();
		Query q = s.getNamedQuery("getAllAuctionsBefore");
		q.setDate("end_date", d);
		return q.list();
	}
	public Auction saveAuctionWithUserAndBook(Auction a) throws Exception { //user does not want to be created, and books do want to be created
		Session s = sessionFactory.getCurrentSession();
			User u = a.getUser();
			u = (User)s.get(User.class,u.getUserId());
			a.setUser(u);
			if(u!=null) {
				s.saveOrUpdate(a);
			}else {
				throw new Exception("Invalid: User not in database");
			}
			return a;
		
	}
	public Auction saveAuctionWithUserAndBookId(Auction a) throws Exception { //user does not want to be created, and books do want to be created
		Session s = sessionFactory.getCurrentSession();
			User u = a.getUser();
			u = (User)s.get(User.class,u.getUserId());
			Book b = a.getBook();
			b = (Book) s.get(Book.class, b.getBookId());
			a.setUser(u);
			a.setBook(b);
			if(u!=null && b!= null) {
				s.saveOrUpdate(a);
			}else {
				throw new Exception("Invalid: User or Book not in database");
			}
			return a;
		
	}
	public Auction getAuctionById(int id) {
		Session s = sessionFactory.getCurrentSession();
		return (Auction) s.get(Auction.class, id);
	}
}
