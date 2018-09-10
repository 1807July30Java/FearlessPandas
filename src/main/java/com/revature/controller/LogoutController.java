package com.revature.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
@RequestMapping("/Logout")
public class LogoutController {
	@RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<String> logout(HttpServletRequest req,HttpServletResponse res) {
		System.out.println("logging out");
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			System.out.println("really logging out");
			new SecurityContextLogoutHandler().logout(req, res,auth);
		}	
      return new ResponseEntity<>("LogoutPage",HttpStatus.OK);
    }
}
