package com.revature.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.Book;

@Repository(value = "bookRepository")
@Transactional
@EnableTransactionManagement
public class BookRepository {
	@Autowired
	SessionFactory sessionFactory;
	public Book saveBook(Book B) {
		Session s = sessionFactory.getCurrentSession();
		s.save(B);
		return B;
	}
	public Book getBookById(int id) {
		Session s = sessionFactory.getCurrentSession();
		return (Book) s.get(Book.class, id);
	}
}
