package com.revature.controller;

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
    public String getProfile() {
        return "forward:/static/views/profile.html";
    }

    @GetMapping("/profileJS")
    public String getProfileJS() {
        return "forward:static/js/profile.js";
    }
}
