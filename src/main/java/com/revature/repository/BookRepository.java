package com.revature.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		Book book = null;
		book = (Book) s.get(Book.class, bookId);		
		return book;
	}
	public List<Book> getBooksByInfo(String title, String author, String publisher){
		Session s = sessionFactory.getCurrentSession();
		Query q = s.getNamedQuery("getBooksByGeneralInfo");
		q.setString("title", title);
		q.setString("author", author);
		q.setString("publisher", publisher);
		return q.list();
	}
	public List<Book> getBooksByArgs(List<String> args ){
		Session s = sessionFactory.getCurrentSession();
		List<Book> ret = new ArrayList<>();
		Query q = s.getNamedQuery("getBookByinfo");
		Iterator<String> i = (Iterator<String>) args.iterator();
		  while(i.hasNext()) {
			  String a0 = i.next();
			  Iterator j = i;
			  while(j.hasNext()) {
				  String a1 = (String) j.next();
				  Iterator k = j;
				  while(k.hasNext()) {
					  q.setString("title",(String) k.next());
					  q.setString("author", a1);
					  q.setString("publisher", a0);
					  ret.addAll(q.list());
					  q.setString("title",(String) k.next());
					  q.setString("author", a0);
					  q.setString("publisher", a1);
					  ret.addAll(q.list());
					  q.setString("title",a1);
					  q.setString("author",(String) k.next());
					  q.setString("publisher", a0);
					  ret.addAll(q.list());
					  q.setString("title",a1);
					  q.setString("author",a0);
					  q.setString("publisher", (String) k.next());
					  ret.addAll(q.list());
					  q.setString("title",a0);
					  q.setString("author",(String) k.next());
					  q.setString("publisher", a1);
					  ret.addAll(q.list());
				  }
			  }
		  }
		  return ret;
	}
	
}
