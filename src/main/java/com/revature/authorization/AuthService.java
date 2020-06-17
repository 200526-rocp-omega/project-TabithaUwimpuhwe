package com.revature.authorization;

import javax.servlet.http.HttpSession;

import com.revature.exceptions.NotLoggedInException;
import com.revature.exceptions.RoleNotAllowedException;
import com.revature.models.User;

public class AuthService {

	public static void guard(HttpSession session, String...roles) {
//		User currentUser = session == null ? null : (User) session.getAttribute("currentUser");
//		if(session == null || currentUser == null) {
//			throw new NotLoggedInException();
//		}
		User currentUser = guard(session);
		
		boolean found = false;
		String role = currentUser.getRole().getRole();
		for(String allowedRole : roles) {
			if(allowedRole.equals(role)) {
				found = true;
				break;
			}
		}

		if(!found) {
			throw new RoleNotAllowedException();
		}
	}

	/*this is what works with the public User findUserById(int id) in the UserController
	User currentUser =
			 * session == null ? null : (User) session.getAttribute("currentUser");
			 * if(session == null || currentUser == null) { throw new
			 * NotLoggedInException(); }
			 * 
			 * 
			 * String role = currentUser.getRole().getRole(); if(!role.equals("Employee") &&
			 * !role.equals("Admin") && currentUser.getId() != id) { // The User does not
			 * have permission throw new NotLoggedInException(); } */
	public static void guard(HttpSession session, int id, String...roles) {
		try {
			guard(session, roles);
		} catch(RoleNotAllowedException e) {
			User current = (User) session.getAttribute("currentUser");
			if(id != current.getUserId()) {
				throw e;
			}
		}
	}
	
	public static User guard(HttpSession session) {
		User currentUser = session == null ? null : (User) session.getAttribute("currentUser");
		if(session == null || currentUser == null) {
			throw new NotLoggedInException();
		}

		return currentUser;
	}
}