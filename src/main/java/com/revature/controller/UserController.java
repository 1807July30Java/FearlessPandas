package com.revature.controller;
import com.revature.domain.User;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController("userController")
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value="/all", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers() {
    	ResponseEntity<List<User>> R = new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    	return  R;
    }
}
