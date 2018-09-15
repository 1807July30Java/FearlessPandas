package com.revature.controller;

import com.revature.beans.UpdateInfo;
import com.revature.domain.User;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController("userController")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest req) {
        ResponseEntity<List<User>> R = new ResponseEntity<>(userService.getUsers((int) req.getSession().getAttribute("id")), HttpStatus.OK);
        return R;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(HttpServletRequest req, @PathVariable int id) {
        try {
            return new ResponseEntity<>(userService.getUserById(req, id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        try {
            return new ResponseEntity<>(userService.getUserByName(username), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/this")
    public ResponseEntity<User> getThisUser(HttpServletRequest req, HttpServletResponse res) {
        HttpSession ses = req.getSession(false);
        if (ses != null && ses.getAttribute("id") != null) {
            return new ResponseEntity<>(userService.getUserById(req, (int) req.getSession(false).getAttribute("id")), HttpStatus.OK);
        } else {
            try {
                res.sendRedirect("../login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @PostMapping("/new")
    public String addUser(HttpServletRequest req, @RequestBody User u) {
        try {
            if (userService.getUserByName(u.getUsername()) == null) {
                userService.addUser(u);
                req.getSession(true).setAttribute("id", u.getUserId());
                return ("profile");
            } else {
                return ("register");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ("Failed to create user");
        }
    }


    @PostMapping("/updateInfo")
    public String updateUser(HttpServletRequest req, @RequestBody UpdateInfo info) {
        HttpSession ses = req.getSession(false);
        if (ses != null && ses.getAttribute("id") != null) {
            int userId = (int) ses.getAttribute("id");
            String username = info.getUsername();
            String fName = info.getFirstName();
            String lName = info.getLastName();
            if (new ResponseEntity<User>(userService.updateUser(username, fName, lName, userId), HttpStatus.OK) != null) {
                return "/profile";
            } else {
                return "/update";
            }


        } else {
            System.out.println("failed");
            return "/login";
        }
    }

    @PostMapping("/updatePass")

    public String updatePassword(HttpServletRequest req, @RequestBody UpdateInfo info){
    	HttpSession ses = req.getSession(false);
    	if(ses!= null && ses.getAttribute("id")!= null) {
    		User u = userService.getUserById(req, (int)ses.getAttribute("id"));
        	User authenticatedUser = userService.getUserByLogin(u.getUsername(),info.getOldPassword());
        	if(authenticatedUser != null) {
	    		String oldPassword = info.getOldPassword();
	    		String newPassword = info.getNewPassword();
	    		System.out.println(oldPassword + "***********"+ newPassword);
	    		int id = (int) ses.getAttribute("id");
	    		
    		//System.out.println("************************************************** uSerr "+userService.updatePassword(oldPassword, newPassword, id));
    		
    			new ResponseEntity<>(userService.updatePassword(oldPassword, newPassword, id),HttpStatus.OK);
    			
    			ses.invalidate();
    			
    			return "/login";
    		}else {
    			return "/updatePass";
    		}
        	
    	
    }else {
		
		return "/login";
	}

    }
   

}
