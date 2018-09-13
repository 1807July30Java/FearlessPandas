package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Credentials;
import com.revature.domain.User;
import com.revature.service.UserService;

@Controller("loginController")
@RequestMapping("/login")
public class LoginController {
	@Autowired
    private UserService userService;
	
	@GetMapping
	public String getLogin(){
		return "forward:/static/Login.html";
	}
	 @PostMapping
	 @ResponseBody
	    public ResponseEntity<String> loginUser(HttpServletRequest req, @RequestBody Credentials credentials){
		 System.out.println("Logging in");
	    	try {
	    		User u = userService.getUserByLogin(credentials.getUsername(),credentials.getPass());
	    		if(u != null) {
	    			req.getSession(true).setAttribute("id", u.getUserId());
	    			return new ResponseEntity<>("Successfully logged in as" + u.getUsername(),HttpStatus.OK);
	    		}else {
	    			return new ResponseEntity<>("Username or Password are incorrect",HttpStatus.OK);
	    		}
	    		
	    	}catch 	(Exception e) {
	    		e.printStackTrace();
	    	}
	    	return null;
	    }

}
