package com.revature.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("loginController")
@RequestMapping("/Log")
public class LoginController {
	@GetMapping
	@ResponseBody
	public ResponseEntity<String> getLogin(){
		return new ResponseEntity<>("Login Page",HttpStatus.OK);
	}
}
