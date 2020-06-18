package com.revature.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.authorization.AuthService;
import com.revature.controllers.AccountController;
import com.revature.controllers.UserController;
import com.revature.exceptions.NotLoggedInException;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.templates.MessageTemplate;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = -4854248294011883310L;
	private static final UserController userController = new UserController();
	private static final AccountController accountController = new AccountController();
	private static final ObjectMapper om = new ObjectMapper();
	private static final UserService service = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404);
		final String URI = req.getRequestURI().replace("/rocp-project", "").replaceFirst("/", "");

		String[] portions = URI.split("/");

		try {
			switch (portions[0]) {
			case "users":
				if (portions.length == 2) {

					int id = Integer.parseInt(portions[1]);
					User u = userController.findUserById(req.getSession(false), id);
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(u));
				} else {

					List<User> all = userController.findAllUsers(req.getSession(false));
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(all));
				}
				break;

			case "accounts":
				if (portions.length == 2) {
					int id = Integer.parseInt(portions[1]);
					Account a = accountController.findAccountById(req.getSession(false), id);
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(a));
				} else {
					List<Account> all = accountController.findAllAccounts(req.getSession(false));
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(all));
				}
				break;
				
			case "accounts/status": {
				int id = Integer.parseInt(portions[1]);
				Account b = accountController.findAccountByStatus(req.getSession(false), id);
				res.setStatus(200);
				res.getWriter().println(om.writeValueAsString(b));
			}
				break;

			case "accounts/type": {
				int id = Integer.parseInt(portions[1]);
				Account c = accountController.findAccountByType(req.getSession(false), id);
				res.setStatus(200);
				res.getWriter().println(om.writeValueAsString(c));
			}
				break;

			case "balance": {
				int id = Integer.parseInt(portions[1]);
				Account c = accountController.findAccountBalanceById(req.getSession(false), id);
				res.setStatus(200);
				res.getWriter().println(om.writeValueAsString(c));
			}
				break;
			}
		} catch (NotLoggedInException e) {
			res.setStatus(401);
			MessageTemplate message = new MessageTemplate("The incoming token has expired");

			res.getWriter().println(om.writeValueAsString(message));
		} catch (NumberFormatException e) {
			res.setStatus(400);
			MessageTemplate message = new MessageTemplate("The id provided was not an integer");

			res.getWriter().println(om.writeValueAsString(message));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/rocp-project", "").replace("/", "");

		String[] portions = URI.split("/");

		try {
			switch (portions[0]) {

			case "logout":
				if (userController.logout(req.getSession(false))) {
					res.setStatus(200);
					res.getWriter().println("You have been successfully logged out");
				} else {
					res.setStatus(400);
					res.getWriter().println("You were not logged in to begin with");
				}
				break;
				
			case "accounts":
				HttpSession session = req.getSession(false);
				session = req.getSession(false);
				AuthService.guard(session, "Admin", "Employee");
				Account a = om.readValue(req.getReader(), Account.class);
				accountController.createAccountById(session, a);
				System.out.println(a);
				res.setStatus(201);
				res.getWriter().println(om.writeValueAsString(a));		
				break;
				
			case "users":
				session = req.getSession(false);
				AuthService.guard(session, "Admin");
				
				User u1 = om.readValue(req.getReader(), User.class);
				userController.createUser(session, u1);
				System.out.println(u1);
				res.setStatus(201);
				res.getWriter().println(om.writeValueAsString(u1));		
				break;
			}
		} catch (NotLoggedInException e) {
			res.setStatus(401);
			MessageTemplate message = new MessageTemplate("The incoming token has expired");
			res.getWriter().println(om.writeValueAsString(message));
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404);
		final String URI = req.getRequestURI().replace("/rocp-project", "").replace("/", "");

		String[] portions = URI.split("/");

		try {
			switch (portions[0]) {

			case "users":
				HttpSession session = req.getSession(false);
				User u = (User) session.getAttribute("currentUser");
				AuthService.guard(session, u.getUserId(), "Admin");

				User u1 = om.readValue(req.getReader(), User.class);
				userController.updateUser(session, u);
				System.out.println(u);
				res.setStatus(200);
				res.getWriter().println(om.writeValueAsString(u1));
				break;

			case "accounts":
				session = req.getSession(false);
				AuthService.guard(session, "Admin");
				Account a = om.readValue(req.getReader(), Account.class);
				accountController.updateAccountById(session, a);
				System.out.println(a);
				res.setStatus(200);
				res.getWriter().println(om.writeValueAsString(a));
				break;

			case "logout":
				if (userController.logout(req.getSession(false))) {
					res.setStatus(200);
					res.getWriter().println("You have been successfully logged out");
				} else {
					res.setStatus(400);
					res.getWriter().println("You were not logged in to begin with");
				}
				break;
			}
		} catch (NotLoggedInException e) {
			res.setStatus(401);
			MessageTemplate message = new MessageTemplate("The incoming token has expired");
			res.getWriter().println(om.writeValueAsString(message));
		}
	}

}