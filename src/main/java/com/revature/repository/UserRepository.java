package com.revature.repository;

import com.revature.domain.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
@Repository(value = "userRepository")
@Transactional
@EnableTransactionManagement
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<User> getUsers() {
        Session s = sessionFactory.getCurrentSession();
        Query q = s.getNamedQuery("getUsers");
        return (ArrayList<User>)q.list();
    }

    public User persisUser(User user) {
        Session s = sessionFactory.getCurrentSession();
        s.persist(user);
        return user;
    }
}
