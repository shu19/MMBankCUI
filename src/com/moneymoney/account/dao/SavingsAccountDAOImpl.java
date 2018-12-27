package com.moneymoney.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.account.util.DBUtil;
import com.moneymoney.exception.AccountNotFoundException;

public class SavingsAccountDAOImpl implements SavingsAccountDAO {

	public SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ACCOUNT (account_hn,account_bal,salary,odLimit,type) VALUES(?,?,?,?,?)");
		preparedStatement.setString(1, account.getBankAccount().getAccountHolderName());
		preparedStatement.setDouble(2, account.getBankAccount().getAccountBalance());
		preparedStatement.setBoolean(3, account.isSalary());
		preparedStatement.setObject(4, null);
		preparedStatement.setString(5, "SA");
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DBUtil.commit();
		return account;
		
	}

	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		List<SavingsAccount> savingsAccounts = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM ACCOUNT");
		while (resultSet.next()) {// Check if row(s) is present in table
			int accountNumber = resultSet.getInt(1);
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			boolean salary = resultSet.getBoolean("salary");
			SavingsAccount savingsAccount = new SavingsAccount(accountNumber, accountHolderName, accountBalance,
					salary);
			savingsAccounts.add(savingsAccount);
		}
		DBUtil.commit();
		return savingsAccounts;
	}
	
	@Override
	public void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException {
		Connection connection = DBUtil.getConnection();
		connection.setAutoCommit(false);
		PreparedStatement preparedStatement = connection.prepareStatement
				("UPDATE ACCOUNT SET account_bal=? where account_id=?");
		preparedStatement.setDouble(1, currentBalance);
		preparedStatement.setInt(2, accountNumber);
		preparedStatement.executeUpdate();
	}
	
	@Override
	public SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement
				("SELECT * FROM account where account_id=?");
		preparedStatement.setInt(1, accountNumber);
		ResultSet resultSet = preparedStatement.executeQuery();
		SavingsAccount savingsAccount = null;
		if(resultSet.next()) {
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			boolean salary = resultSet.getBoolean("salary");
			savingsAccount = new SavingsAccount(accountNumber, accountHolderName, accountBalance,
					salary);
			return savingsAccount;
		}
		throw new AccountNotFoundException("Account with account number "+accountNumber+" does not exist.");
	}
	
	public SavingsAccount updateAccount(SavingsAccount account) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public boolean deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		Connection connection=DBUtil.getConnection();
		String query="DELETE FROM ACCOUNT WHERE account_id=?";
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		preparedStatement.setInt(1, accountNumber);
		
		boolean result=preparedStatement.execute();
		DBUtil.commit();
		return result;
		/*System.out.println("resultSet : "+resultSet);
		SavingsAccount savingsAccount = null;
		if(resultSet.next()) {
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			boolean salary = resultSet.getBoolean("salary");
			savingsAccount = new SavingsAccount(accountNumber, accountHolderName, accountBalance,
					salary);
			return savingsAccount;
		}
		throw new AccountNotFoundException("Account with account number "+accountNumber+" does not exist.");*/
	}	

	@Override
	public Set<SavingsAccount> getSortedAccounts(int choice) throws ClassNotFoundException, SQLException {
		
		Set<SavingsAccount> savingsAccounts = null;
		
		switch(choice){
		case 1:
			savingsAccounts = new TreeSet<SavingsAccount>(new Comparator<SavingsAccount>() {

				@Override
				public int compare(SavingsAccount o1, SavingsAccount o2) {
					
					return o1.getBankAccount().getAccountNumber()-o2.getBankAccount().getAccountNumber();
				}
			});
			break;
		case 2:
			savingsAccounts = new TreeSet<SavingsAccount>(new Comparator<SavingsAccount>() {

				@Override
				public int compare(SavingsAccount o1, SavingsAccount o2) {
					
					return o1.getBankAccount().getAccountHolderName().compareTo(o2.getBankAccount().getAccountHolderName());
					
				}
			});

			break;
		case 3:
			savingsAccounts = new TreeSet<SavingsAccount>(new Comparator<SavingsAccount>() {

				@Override
				public int compare(SavingsAccount o1, SavingsAccount o2) {
					
					return (int) (o1.getBankAccount().getAccountBalance()-o2.getBankAccount().getAccountBalance());
					
				}
			});

			break;
		default:
			savingsAccounts =new TreeSet<SavingsAccount>();
			break;
		}
		
		
		Connection connection = DBUtil.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM ACCOUNT");
		while (resultSet.next()) {// Check if row(s) is present in table
			
			int accountNumber = resultSet.getInt(1);
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			boolean salary = resultSet.getBoolean("salary");
			SavingsAccount savingsAccount = new SavingsAccount(accountNumber, accountHolderName, accountBalance,
					salary);
			savingsAccounts.add(savingsAccount);
		}
//		DBUtil.commit();
		return savingsAccounts;
		
	}


}
