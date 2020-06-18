package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.exceptions.NotLoggedInException;
import com.revature.models.User;
import com.revature.templates.LoginTemplate;

public class UserService {
	
		private IUserDAO dao = new UserDAO();
	
		public int insert(User u) {
			return dao.insert(u);
		}
	
		public List<User> findAll() {
			return dao.findAll();
		}
	
		public User findById(int id) {
			return dao.findById(id);
		}
	
		public User findByUsername(String username) {
			return dao.findByUserName(username);
		}
	
		public int update(User u) {
			return dao.update(u);
		}
		
		public User login(LoginTemplate lt) {
			
			User userFromDB = findByUsername(lt.getUsername());
			System.out.println(userFromDB);
			
			if(userFromDB == null) {
				return null;
			}
			
			if(userFromDB.getPassword().equals((lt.getPassword()))) {
				
				return userFromDB;
			}
			return null;
		}
		
		public void logout(HttpSession session) {
			if(session == null || session.getAttribute("currentUser") == null) {
				throw new NotLoggedInException("User must be logged in, in order to logout.");
			}
			session.invalidate();
		}
}
