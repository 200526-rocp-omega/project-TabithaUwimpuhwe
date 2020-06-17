package com.revature;

import com.revature.dao.UserDAO;
import com.revature.dao.IUserDAO;
import com.revature.models.User;
import com.revature.models.Role;

public class Driver {

	public static void main(String[] args) {
		
		IUserDAO dao = new UserDAO();		
		//add user to DB
		User user = new User(0, "username16", "password", "first", "last", "email16@yahoo.com", new Role(1, "Standard"));
		System.out.println(dao.insert(user));
		
		for (User u : dao.findAll()) {
			System.out.println(u);
		}
	}

}
