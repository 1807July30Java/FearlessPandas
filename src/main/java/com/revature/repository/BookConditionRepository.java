package com.revature.repository;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.BookCondition;

@Repository(value = "bookConditionRepository")
@Transactional
@EnableTransactionManagement
public class BookConditionRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	public BookCondition getConditionByBookId(int conditionId) {
		Session s = sessionFactory.getCurrentSession();
//		Query q = s.getNamedQuery("getBookConditionById");
//		q.setInteger("conditionId", conditionId);
		BookCondition condition = null;
		condition = (BookCondition) s.get(BookCondition.class, conditionId);
		return condition;
	}

}
