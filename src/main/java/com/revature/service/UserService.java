package com.revature.service;

import com.revature.domain.User;
import com.revature.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service(value = "userService")
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers(int id) {
    	List<User> res = userRepository.getUsers();
        for(User u:res) {
        	if(u.getUserId() != id) {
        		u.setPassword("");
    			u.setAddresses(null);
    			u.setEmail("");
    			u.setlName("");
    			u.setfName("");
    			u.setPayEmail("");
        	}
        }
    	return res;
    }

    public User getUserById(HttpServletRequest req, int id) {
    	User u = userRepository.getUserById(id);
    	int temp = (int) req.getSession(false).getAttribute("id");
    	if(temp != u.getUserId()) {
			u.setPassword("");
			u.setAddresses(null);
			u.setEmail("");
			u.setlName("");
			u.setfName("");
			u.setPayEmail("");
		}
    	return u;
    }
    public User getUserByLogin(String username, String password) {
    	return userRepository.getUserByLogin(username,password.hashCode());
    }
    public User getUserByLogin(String username, int password) {
    	return userRepository.getUserByLogin(username, password);
    }
    public User getUserByName(String username) {
    	User u = userRepository.getUserByName(username);
    	if(u != null) {
	    	u.setPassword("");
	    	u.setAddresses(null);
	    	u.setEmail("");
	    	u.setlName("");
	    	u.setfName("");
	    	u.setPayEmail("");
    	}
    	return u;
    }
    public void addUser(User user) {
        userRepository.saveUser(user);
    }
    
    public User updateUser(String username, String fName, String lName,int userId) {
    	return userRepository.updateUser(username, fName, lName, userId);
    }
    
    public User updatePassword(String oldPassword, String newPassword, int userId) {
    	System.out.println("**************************************************ServicePa "+userRepository.updatePassword(oldPassword, newPassword, userId));
    	return userRepository.updatePassword(oldPassword, newPassword, userId);
    }
}
