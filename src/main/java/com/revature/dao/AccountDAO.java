package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.models.Account;
import com.revature.models.AccountStatus;
import com.revature.models.AccountType;
import com.revature.util.ConnectionUtil;

public class AccountDAO implements IAccountDAO {


	@Override
	public int insert(Account a) {
		
		try (Connection conn = ConnectionUtil.getConnection()){	
			
			String sql = "INSERT INTO ACCOUNTS (balance, STATUS_ID, TYPE_ID) VALUES (?, ?, ?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
		
			stmt.setDouble(1, a.getBalance());
			stmt.setInt(2, a.getStatus().getStatusId());
			stmt.setInt(3, a.getType().getTypeId());	
					
			return stmt.executeUpdate();
			
		} catch(SQLException e) {		
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int update(Account a) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "UPDATE ACCOUNTS SET balance=?, STATUS_ID=?, TYPE_ID=? where id=?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setDouble(1, a.getBalance());
			stmt.setInt(2, a.getStatus().getStatusId());
			stmt.setInt(3, a.getType().getTypeId());
			stmt.setInt(4, a.getAccountId());
			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public List<Account> findAll() {
		
		List<Account> allAccounts = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM  ((ACCOUNTS INNER JOIN ACCOUNT_STATUS ON ACCOUNTS.STATUS_ID = ACCOUNT_STATUS.ID) "
					+ "INNER JOIN ACCOUNT_TYPE ON ACCOUNTS.TYPE_ID = ACCOUNT_TYPE.ID)";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int id = rs.getInt("id");
				double balance = rs.getDouble("balance");
				int statusId = rs.getInt ("status_id"); 
				int typeId = rs.getInt ("type_id"); 
				String accountStatus = rs.getString("status");
				String accountType = rs.getString("type");
				
				AccountStatus as = new AccountStatus(statusId, accountStatus);
				AccountType at = new AccountType(typeId, accountType);
				
				Account a = new Account(id, balance, as, at);

				allAccounts.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList();
		}

		return allAccounts;
	}

	
	@Override
	public Account findAccountById(int id) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM  ((ACCOUNTS INNER JOIN ACCOUNT_STATUS ON ACCOUNTS.STATUS_ID = ACCOUNT_STATUS.ID) "
					+ "INNER JOIN ACCOUNT_TYPE ON ACCOUNTS.TYPE_ID = ACCOUNT_TYPE.ID) where ACCOUNTS.id =?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				double balance = rs.getDouble("balance");
				int statusId = rs.getInt ("status_id"); 
				int typeId = rs.getInt ("type_id"); 
				String accountStatus = rs.getString("status");
				String accountType = rs.getString("type");

				AccountStatus as = new AccountStatus(statusId, accountStatus);
				AccountType at = new AccountType(typeId, accountType);
				return new Account(id, balance, as, at);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public Account findAccountByStatus(int id) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM  ((ACCOUNTS INNER JOIN ACCOUNT_STATUS ON ACCOUNTS.STATUS_ID = ACCOUNT_STATUS.ID) "
					+ "INNER JOIN ACCOUNT_TYPE ON ACCOUNTS.TYPE_ID = ACCOUNT_TYPE.ID) where ACCOUNTS.Status_id =?";
																	
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				double balance = rs.getDouble("balance");
				int statusId = rs.getInt ("status_id"); 
				int typeId = rs.getInt ("type_id"); 
				String accountStatus = rs.getString("status");
				String accountType = rs.getString("type");

				AccountStatus as = new AccountStatus(statusId, accountStatus);
				AccountType at = new AccountType(typeId, accountType);

				return new Account(id, balance, as, at);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Account findAccountByType(int id) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM  ((ACCOUNTS INNER JOIN ACCOUNT_STATUS ON ACCOUNTS.STATUS_ID = ACCOUNT_STATUS.ID) "
					+ "INNER JOIN ACCOUNT_TYPE ON ACCOUNTS.TYPE_ID = ACCOUNT_TYPE.ID) where ACCOUNTS.Type_id =?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				double balance = rs.getDouble("balance");
				int statusId = rs.getInt ("status_id"); 
				int typeId = rs.getInt ("type_id"); 
				String accountStatus = rs.getString("status");
				String accountType = rs.getString("type");

				AccountStatus as = new AccountStatus(statusId, accountStatus);
				AccountType at = new AccountType(typeId, accountType);

				return new Account(id, balance, as, at);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account findAccountBalanceById(int id) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT balance FROM  ((ACCOUNTS INNER JOIN ACCOUNT_STATUS ON ACCOUNTS.STATUS_ID = ACCOUNT_STATUS.ID) "
					+ "INNER JOIN ACCOUNT_TYPE ON ACCOUNTS.TYPE_ID = ACCOUNT_TYPE.ID) where ACCOUNTS.ID=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				double balance = rs.getDouble("balance");
				int statusId = rs.getInt ("status_id"); 
				int typeId = rs.getInt ("type_id"); 
				String accountStatus = rs.getString("status");
				String accountType = rs.getString("type");

				AccountStatus as = new AccountStatus(statusId, accountStatus);
				AccountType at = new AccountType(typeId, accountType);

				return new Account(id, balance, as, at);				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
