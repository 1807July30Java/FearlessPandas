package com.revature.controller;
import com.revature.beans.Credentials;
import com.revature.beans.UpdateInfo;
import com.revature.domain.User;


import com.revature.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController("userController")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
    	ResponseEntity<List<User>> R = new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    	return  R;
    }
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<User> getUserById(HttpServletRequest req, @PathVariable int id){
    	return new ResponseEntity<>(userService.getUserById(req, id), HttpStatus.OK);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
    	return new ResponseEntity<>(userService.getUserByName(username),HttpStatus.OK);
    }
    @PostMapping("/new")
    public ResponseEntity<String> addUser(@RequestBody User u) throws IOException{
    	try {
    		if(userService.getUserByName(u.getUsername()) == null) {
    			userService.addUser(u);
    			return new ResponseEntity<>("Successfully created user",HttpStatus.OK);
    		}else {
    			return new ResponseEntity<>("this username already exists",HttpStatus.OK);
    		}
    	}catch 	(Exception e) {
    		e.printStackTrace();
    		return new ResponseEntity<>("Failed to create user",HttpStatus.BAD_REQUEST);	
    	}
    }

 
    @PostMapping("/updateInfo")
    public ResponseEntity<User> updateUser(HttpServletRequest req, @RequestBody UpdateInfo info){
    	HttpSession ses = req.getSession(false);
    	if(ses!= null && ses.getAttribute("id")!=null) {	
    	int userId = (int) ses.getAttribute("id");
    	String username = info.getUsername();
    	String fName = info.getFirstName();
    	String lName = info.getLastName();
    
    	
    	return new ResponseEntity<User>(userService.updateUser(username, fName, lName, userId), HttpStatus.OK);
    	}else {
    		System.out.println("failed");
    		return null;
    	}
    }
    
    public ResponseEntity<User> updatePassword(HttpServletRequest req, @RequestBody UpdateInfo info){
    	HttpSession ses = req.getSession(false);
    	if(ses!= null && ses.getAttribute("id")!= null) {
    		String oldPassword = info.getOldPassword();
    		String newPassword = info.getNewPassword();
    		int userId = (int) ses.getAttribute("id");
    		return new ResponseEntity<User>(userService.updatePassword(oldPassword, newPassword, userId),HttpStatus.OK);
    	}else {
    		System.out.println("failed");
    		return null;
    	}
    }
   


   

}
