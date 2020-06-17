package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

public interface IAccountDAO {

	public int insert (Account a);
	public int update(Account a);
	
	public List<Account> findAll();	
	public Account findAccountById(int id);
	
	public Account findAccountByStatus(int id);
	public Account findAccountByType(int id);
	
	public Account findAccountBalanceById (int id); 	
}
