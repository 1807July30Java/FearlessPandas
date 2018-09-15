package com.revature.repository;

import com.revature.beans.CloseAuctionTask;
import com.revature.domain.Auction;
import com.revature.domain.Book;
import com.revature.domain.BookCondition;
import com.revature.domain.Genre;
import com.revature.domain.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

@Repository(value = "auctionRepository")
@Transactional
@EnableTransactionManagement
public class AuctionRepository {
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	TaskScheduler scheduler;
	//@Autowired
	//TaskExecutor execute = new TaskExecutor();
	public Auction saveAuction(Auction a) {
		Session s;
		try {
			 s= sessionFactory.getCurrentSession();
		}catch(Exception e) {
			s = sessionFactory.openSession();
		}
        s.saveOrUpdate(a);
        return a;
	}
	public List<Auction> getAllAuctions(){
		Session s;
		try {
			 s= sessionFactory.getCurrentSession();
		}catch(Exception e) {
			s = sessionFactory.openSession();
		}
		Query q = s.getNamedQuery("getAllAuctions");
		return q.list();
	}
	public List<Auction> getAllAuctionsBefore(Date d){
		Session s;
		try {
			 s= sessionFactory.getCurrentSession();
		}catch(Exception e) {
			s = sessionFactory.openSession();
		}
		Query q = s.getNamedQuery("getAllAuctionsBefore");
		q.setDate("end_date", d);
		return q.list();
	}
	public List<Auction> getAllUserAuctions(int userId) {
		Session s;
		try {
			 s= sessionFactory.getCurrentSession();
		}catch(Exception e) {
			s = sessionFactory.openSession();
		}
		Query q = s.getNamedQuery("getUserAuctions");
		q.setInteger("userId", userId);
		return q.list();
	}
	public Auction saveAuctionWithUserAndBook(Auction a) throws Exception { //Save auction with new book not yet in db
			Session s;
			try {
				 s= sessionFactory.getCurrentSession();
			}catch(Exception e) {
				s = sessionFactory.openSession();
			}
			User u = a.getUser();
			Set<Genre> G = a.getBook().getGenres();
			BookCondition BC = a.getBook().getCondition();
			Query Q = s.getNamedQuery("getConditionByName");
			Q.setString("name",BC.getName());
			List<BookCondition> pBC = Q.list();
			if( pBC.size()>0) {
				a.getBook().setCondition(pBC.get(0));
			}
			HashSet<Genre> processedGenres = new HashSet<Genre>(0);
			for(Genre g:G) { ///ensures that duplicate genres are not added;
				Query q = s.getNamedQuery("getGenreByName");
				q.setString("name",	g.getName());
				List<Genre> gg =  q.list();
				if ( gg.size()>0) {
				      processedGenres.add(gg.get(0));
				}else {
					processedGenres.add(g);
				}
			}
			a.getBook().setGenres(processedGenres);
			u = (User)s.get(User.class,u.getUserId());
			a.setUser(u);
			if(u!=null && 	a.isClosed()<1) {
				s.saveOrUpdate(a);
				Date d = new Date(a.getEndDate().getTime() + 14400000);
				scheduler.schedule(new CloseAuctionTask(a,this),d );
			}else {
				throw new Exception("Invalid: User not in database or Auction is closed");
			}
			return a;
		
	}
	public Auction saveAuctionWithUserAndBookId(Auction a) throws Exception { //saves auction with book that is already in db
			Session s;
			try {
				 s= sessionFactory.getCurrentSession();
			}catch(Exception e) {
				s = sessionFactory.openSession();
			}
			User u = a.getUser();
			u = (User)s.get(User.class,u.getUserId());
			Book b = a.getBook();
			b = (Book) s.get(Book.class, b.getBookId()); 	
			a.setUser(u);
			a.setBook(b);
			if(u!=null && b!= null && a.isClosed()<1) {
				s.saveOrUpdate(a);
				Date d = new Date(a.getEndDate().getTime() + 14400000);
				scheduler.schedule(new CloseAuctionTask(a,this),d);
			}else {
				throw new Exception("Invalid: User or Book not in database or Auction is closed");
			}
			return a;
			
	}
	public Auction getAuctionById(int id) {
		Session s;
		try {
			 s= sessionFactory.getCurrentSession();
		}catch(Exception e) {
			s = sessionFactory.openSession();
		}
		return (Auction) s.get(Auction.class, id);
	}
	public void setClosed(Auction a) {
		Session s;
		try { //necessary because Task executes on different thread, outside scope of sessionFactory.
		 s = sessionFactory.getCurrentSession();
		}catch(Exception e) {
			 s = sessionFactory.openSession();
		}
		a = (Auction) s.get(Auction.class, a.getAuctionId());
		a.setClosed(1);
		s.update(a);
		
	}
	public int isClosed(Auction a) {
		Session s;
		try { //necessary because Task executes on different thread, outside scope of sessionFactory.
		 s = sessionFactory.getCurrentSession();
		}catch(Exception e) {
			 s = sessionFactory.openSession();
		}
		a = (Auction)s.get(Auction.class, a.getAuctionId());
		System.out.println("Is a closed???????????????????????????????" + a.isClosed());
		return a.isClosed();
	}
	public List<Auction> getClosed(){
		Session s;
		try { //necessary because Task executes on different thread, outside scope of sessionFactory.
		 s = sessionFactory.getCurrentSession();
		}catch(Exception e) {
			 s = sessionFactory.openSession();
		}
		Query q = s.getNamedQuery("getClosedAuctions");
		return q.list();
	}
	
	public List<Auction> getAuctionSearch(int bookId, int minimumPrice, int buyItNow, Date endDate){
		Session s = sessionFactory.getCurrentSession();
		if (bookId != 0 && buyItNow != 0 && minimumPrice !=0 && endDate != null) {
			Query q = s.getNamedQuery("getAuctionByBookIdBuyNowMinPrice");
			q.setInteger("bookId", bookId);
			q.setInteger("minimumPrice", minimumPrice);
			q.setInteger("buyItNow", buyItNow);
			q.setDate("endDate", endDate);
			return q.list();
		}
		else if (bookId != 0 && buyItNow != 0) {
			Query q = s.getNamedQuery("getAuctionByBookIdAndBuyNowPrice");
			q.setInteger("bookId", bookId);
			q.setInteger("buyItNow", buyItNow);
			return q.list();
		}else if(bookId != 0 && minimumPrice!=0) {
			Query q = s.getNamedQuery("getAuctionByBookIdAndMinPrice");
			q.setInteger("bookId", bookId);
			q.setInteger("minimumPrice", minimumPrice);
			return q.list();
		}else {
			Query q = s.getNamedQuery("getAuctionByGeneralBook");
			q.setInteger("bookId", bookId);
			return q.list();
		}
		
	}
	
}
