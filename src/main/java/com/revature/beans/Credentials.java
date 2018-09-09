package com.revature.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.revature.domain.User;

@Component
@Scope("prototype")
public class Credentials {

	private String username;
	private String pass;
	private User user;
	public Credentials(String username, String pass) {
		super();
		this.username = username;
		this.pass = pass;
	}
	public Credentials() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
