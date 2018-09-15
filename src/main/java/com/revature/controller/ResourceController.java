package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller("resourceController")
public class ResourceController {

    @GetMapping("/style")
    public String getStyle() {
        return "forward:/static/styles/login.css";
    }

    @GetMapping("/profile")
    public String getProfile(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("id") != null) {
            return "forward:/static/views/profile.html";
        } else {
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

    @GetMapping("/newAuction")
    public String newAuction(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("id") != null) {
            return "forward:/static/views/newAuction.html";
        } else {
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

    @GetMapping("/search")
    public String getSearch(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("id") != null) {
            return "forward:/static/views/search.html";
        } else {
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

    @GetMapping("/auctions")
    public String getAuctions(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("id") != null) {
            return "forward:/static/views/auctions.html";
        } else {
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

    @GetMapping("/update")
    public String getUpdate(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("id") != null) {
            return "forward:/static/views/updateInfo.html";
        } else {
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

    @GetMapping("/updatePass")
    public String getPassUpdate(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("id") != null) {
            return "forward:/static/views/updatePass.html";
        } else {
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

    @GetMapping("/auctionsJS")
    public String getAuctionsJS() {
        return "forward:/static/js/auctions.js";
    }

    @GetMapping("/updateJS")
    public String getUpdateJS() {
        return "forward:/static/js/update.js";
    }

    @GetMapping("/auctionSearchJS")
    public String getAuctionSearch() {

        return "forward:/static/js/auctionSearch.js";
    }
}
