package com.revature.controller;

import com.revature.beans.Credentials;
import com.revature.domain.User;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView loginUser(HttpServletRequest req, @RequestBody Credentials credentials) {
        System.out.println("Logging in");
        try {
            User u = userService.getUserByLogin(credentials.getUsername(), credentials.getPass());
            if (u != null) {
                req.getSession(true).setAttribute("id", u.getUserId());
                return new RedirectView("/profile");
            } else {
                return new RedirectView("/login");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}