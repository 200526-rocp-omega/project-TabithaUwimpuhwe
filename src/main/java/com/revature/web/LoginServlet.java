package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.templates.LoginTemplate;

public class LoginServlet extends HttpServlet {
	
	//will be used to abstract JSON object
//	private ObjectMapper om = new ObjectMapper();
//	private UserService service = new UserService();
	private static final ObjectMapper om = new ObjectMapper();
	private static final UserService service = new UserService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	        throws ServletException, IOException{
	    
		
	    }
	
	@Override
	 protected void doPost(HttpServletRequest req, HttpServletResponse res)
		        throws ServletException, IOException {
		    
		HttpSession session = req.getSession();
		//session mean if two people are logging in at the same time, you could say, 
		//current session could mean you are already logged in -- see what it does in postman
		
		User current = (User) session.getAttribute("currentUser");
				
		//Already logged in
		if(current !=null) {
			
			res.setStatus(400);
			res.getWriter().println("You are already logged in as user " + current.getUsername());
			return;
	
		}
		
		BufferedReader reader = req.getReader();
				
		StringBuilder sb = new StringBuilder();
		
		String line; 
		
		while((line = reader.readLine()) !=null ) {
			sb.append(line);
		}
		/*
		 * The (line = reader.readLine()) part obtains the value for a single line
		 * from the body of the request and stores it into line variable
		 * 
		 * Then the !=null part compares the value of the string null
		 * 
		 * If the redLine() method is null, that means we are at the end
		 */
		
		String body = sb.toString();
		
		LoginTemplate lt = om.readValue(body, LoginTemplate.class); // will need a class that has exactly "username" and "password"	
		System.out.println(lt);
		
		User u = service.login(lt);
		PrintWriter writer = res.getWriter();
		
		if(u == null) {
			//login failed
			res.setStatus(400);//bad request
//			writer.print(u);
			writer.print("username or password was incorrect");
			
			return;
		}
		
		//login successful
		
		
		 session.setAttribute("currentUser", u);
		//This session will help in the project to tell if the users had not login yet - 
		 //need to understand this
		res.setStatus(200);
		
		writer.println(om.writeValueAsString(u));
		res.setContentType("application/json");
		
    }

}
