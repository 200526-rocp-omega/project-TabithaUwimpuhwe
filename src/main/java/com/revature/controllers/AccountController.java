package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.revature.exceptions.NotLoggedInException;
import com.revature.models.Account;

import com.revature.services.AccountService;

public class AccountController {

	private final AccountService accountService = new AccountService();
	
	public Account findAccountById(HttpSession httpSession, int id) {
		return accountService.findAccountById(id);
	}

	public List<Account> findAllAccounts(HttpSession httpSession) {
		return accountService.findAll();
	}

	public Account findAccountByStatus(HttpSession httpSession, int id) {
		return accountService.findAccountByStatus(id);
	}

	public Account findAccountByType(HttpSession httpSession, int id) {
		return accountService.findAccountByType(id);
	}

	public Account findAccountBalanceById(HttpSession httpSession, int id) {
		return accountService.findAccountBalanceById(id);
	}

	public int updateAccountById(HttpSession httpSession, Account a) {
		return accountService.update(a);
	}

	public int createAccountById(HttpSession httpSession, Account a) {
		return accountService.insert(a);
	}
	
}
