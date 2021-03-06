package com.revature.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
// The only way to logout with basic authentication is to close the browser
//https://www.youtube.com/watch?v=poc5dyImbig
//Switch to https in security.xml
@RestController("logoutController")
@RequestMapping("/logout")
public class LogoutController {
	@RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<String> logout(HttpServletRequest req,HttpServletResponse res) {
		System.out.println("logging out");
		
		//Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		HttpSession session = req.getSession(false);
		if(session != null) {
			//new SecurityContextLogoutHandler().logout(req, res,auth);
			session.setAttribute("id", 0);
			session.invalidate(); 
			
		}	
		try {
			res.sendRedirect("login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      return new ResponseEntity<>("Successfully Logged out",HttpStatus.OK);
    }
}
