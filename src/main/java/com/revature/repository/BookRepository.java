package com.revature.repository;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.domain.Book;

@Repository(value = "bookRepository")
@Transactional
public class BookRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Book getBookById(int bookId) {
		Session s = sessionFactory.getCurrentSession();
//		Query q = s.getNamedQuery("getBookById");
//		q.setInteger("bookId", bookId);
		
		Book book = null;
		book = (Book) s.get(Book.class, bookId);		
		return book;
	}
	
}
