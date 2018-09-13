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
    public User getUserById(int id) {
    	Session s = sessionFactory.getCurrentSession();
    	Query q = s.getNamedQuery("getUserById");
    	q.setInteger("uid", id);
    	return (User) q.uniqueResult();
    }
    public User getUserByLogin(String username, int password) {
    	Session s = sessionFactory.getCurrentSession();
    	Query q = s.getNamedQuery("getUserByLogin");
    	q.setString("username",username);
    	q.setInteger("password", password);
    	return (User) q.uniqueResult();
    }
    public User getUserByName(String username) {
    	Session s = sessionFactory.getCurrentSession();
    	Query q = s.getNamedQuery("getUserByName");
    	q.setString("username",username);
    	return (User) q.uniqueResult();
    }
    public User persistUser(User user) {
        Session s = sessionFactory.getCurrentSession();
        s.persist(user);
        return user;
    }
    public User saveUser(User user) {
        Session s = sessionFactory.getCurrentSession();
        s.save(user);
        return user;
    }
    public User updateUser(String username, String fName, String lName, int userId) {
    	Session s = sessionFactory.getCurrentSession();
    	
    	User user = (User) s.get(User.class, userId);
    	
    	if(fName != null) {
    		user.setfName(fName);
    	}
    		
    	if(lName != null) {
    		user.setlName(lName);
    	}
    		
    	if(username!=null) {
    		user.setUsername(username);
    	}
    	s.merge(user);
    	return user;
    	
    }
    
    public User updatePassword(String oldPassword, String newPassword, int userId) {
    	Session s = sessionFactory.getCurrentSession();
    	User user = (User) s.get(User.class, userId);
    	if(oldPassword.hashCode()==user.getPassword()) {
    		user.setPassword(newPassword);
    	}
    	s.merge(user);
    	return user;
    }
    
    
    }
