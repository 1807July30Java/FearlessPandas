package com.revature.controller;
import com.revature.beans.Credentials;
import com.revature.domain.Address;
import com.revature.domain.User;
import javax.servlet.http.HttpServletRequest;

import com.revature.service.AddressService;
import com.revature.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@RestController("userController")
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @RequestMapping(value="/all", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers() {
    	ResponseEntity<List<User>> R = new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    	return  R;
    }
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable int id){
    	ResponseEntity<User> R = new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    	return  R;
    }
    @RequestMapping(value="/newUser", method=RequestMethod.POST)
    @ResponseBody
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
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<User> loginUser(@RequestBody Credentials credentials){
    	try {
    		
    		return new ResponseEntity<>(userService.getUserByLogin(credentials.getUsername(), credentials.getPass()),HttpStatus.OK);
    	}catch 	(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}
