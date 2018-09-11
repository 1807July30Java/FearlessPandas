package com.revature.service;

import com.revature.domain.User;
import com.revature.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
@Scope("Prototype")
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.getUsers();
    }
    public User getUserById(int id) {
    	return userRepository.getUserById(id);
    }
    public User getUserByLogin(String username, String password) {
    	return userRepository.getUserByLogin(username,password);
    }
    public User getUserByName(String username) {
    	return userRepository.getUserByName(username);
    }
    public void addUser(User user) {
        userRepository.saveUser(user);
    }
}
