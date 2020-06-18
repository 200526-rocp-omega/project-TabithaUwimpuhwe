package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.revature.dao.IAccountDAO;
import com.revature.dao.AccountDAO;
import com.revature.exceptions.NotLoggedInException;
import com.revature.models.Account;
import com.revature.templates.LoginTemplate;

public class AccountService {
	
	private IAccountDAO dao = new AccountDAO();
	
	public int insert(Account a) {
		return dao.insert(a);
	}
	
	public int update(Account a) {
		return dao.update(a);
	}

	public List<Account> findAll() {
		return dao.findAll();
	}

	public Account findAccountById(int id) {
		return dao.findAccountById(id);
	}

	public Account findAccountByStatus(int id) {
		return dao.findAccountByStatus(id);
	}

	public Account findAccountByType(int id) {
		return dao.findAccountByType(id);
	}
	
	public Account findAccountBalanceById(int id) {
		return dao.findAccountBalanceById(id);
	}

}
