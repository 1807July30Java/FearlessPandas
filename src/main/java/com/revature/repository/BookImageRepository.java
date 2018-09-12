package com.revature.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.BookImage;

@Repository(value = "bookImageRepository")
@Transactional
@EnableTransactionManagement
//DAO Getting book images by book id
public class BookImageRepository {
	@Autowired
	private SessionFactory sessionFactory;
	public List<BookImage> getImageByBookId(int bookId){
		Session s = sessionFactory.openSession();
		Query q = s.getNamedQuery("getBookImageByBookId");
		q.setInteger("bookId", bookId);
		return q.list();
	}
	
	
	public BookImage getImageById(int imageId){
		Session s = sessionFactory.openSession();
		
		return (BookImage)s.get(BookImage.class, imageId);
		}

}
