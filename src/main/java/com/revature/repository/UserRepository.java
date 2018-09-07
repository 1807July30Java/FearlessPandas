package com.revature.repository;

import com.revature.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository(value = "userRepository")
@Transactional
@EnableTransactionManagement
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<User> getUsers() {
        Session s = sessionFactory.getCurrentSession();
        return s.createQuery("from User").list();
    }

    public User persisUser(User user) {
        Session s = sessionFactory.getCurrentSession();
        s.persist(user);
        return user;
    }
}
