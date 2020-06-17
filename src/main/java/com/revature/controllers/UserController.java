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
	/*All of the HttpSession session above are moved to Authorization class:
	 * public User findUserById(HttpSession session, int id) { User currentUser =
	 * session == null ? null : (User) session.getAttribute("currentUser");
	 * if(session == null || currentUser == null) { throw new
	 * NotLoggedInException(); }
	 * 
	 * 
	 * String role = currentUser.getRole().getRole(); if(!role.equals("Employee") &&
	 * !role.equals("Admin") && currentUser.getId() != id) { // The User does not
	 * have permission throw new NotLoggedInException(); }
	 */
	
	public List<User> findAllUsers(HttpSession httpSession) {
		return userService.findAll();
	}
	/*
	 * public List<User> findAllUsers(HttpSession session) { User currentUser =
	 * session == null ? null : (User) session.getAttribute("currentUser");
	 * if(session == null || currentUser == null) { throw new
	 * NotLoggedInException(); }
	 * 
	 * String role = currentUser.getRole().getRole(); if(!role.equals("Employee") &&
	 * !role.equals("Admin")) { // The User does not have permission throw new
	 * NotLoggedInException(); }
	 */
	
	public User updateUser(HttpSession httpSession) {
		
//		userService.update(u);
		return null;
	}
	
}