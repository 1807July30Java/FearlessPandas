package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("resourceController")
public class ResourceController {

    @GetMapping("/style")
    public String getStyle() {
        return "forward:/static/styles/login.css";
    }

    @GetMapping("/profile")
    public String getProfile(HttpServletRequest req , HttpServletResponse res) {
    	HttpSession session = req.getSession(false);
    	if(session!= null && session.getAttribute("id") != null) {
    		return "forward:/static/views/profile.html";
    	}else {
    		 try {
				System.out.println("tried to redirect");
				res.sendRedirect("login");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return "forward:/static/views/login.html";
    	}
    }

    @GetMapping("/profileJS")
    public String getProfileJS() {
        return "forward:static/js/profile.js";
    }

    @GetMapping("/auctions")
    public String getAuctions() {
        return "forward:/static/views/auctions.html";
    }

    @GetMapping("/auctionsJS")
    public String getAuctionsJS() {
        return "forward:/static/js/auctions.js";
    }
}
