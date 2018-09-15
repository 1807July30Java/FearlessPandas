package com.revature.controller;

import com.revature.beans.Credentials;
import com.revature.domain.User;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller("loginController")
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String getLogin() {
        return "forward:/static/views/login.html";
    }

    @PostMapping
    @ResponseBody
    public String loginUser(HttpServletRequest req, @RequestBody Credentials credentials) {
        System.out.println("Logging in");
        try {
            User u = userService.getUserByLogin(credentials.getUsername(), credentials.getPass());
            if (u != null) {
                req.getSession(true).setAttribute("id", u.getUserId());
                return "/profile";
            } else {
                return "/login";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}