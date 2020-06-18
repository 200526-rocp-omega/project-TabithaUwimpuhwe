package com.revature.controllers;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.revature.exceptions.NotLoggedInException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserController {

	private final UserService userService = new UserService();

	public boolean logout(HttpSession session) {
		try {
			userService.logout(session);
		} catch(NotLoggedInException e) {
			return false;
		}
		return true;
	}

	public User findUserById(HttpSession httpSession, int id) {
		return userService.findById(id);
	}
	
	
	public List<User> findAllUsers(HttpSession httpSession) {
		return userService.findAll();
	}
	
	public int updateUser(HttpSession httpSession, User u) {
		return userService.update(u);
	}
	
	public int createUser(HttpSession httpSession, User u) {
		return userService.insert(u);
	}
}