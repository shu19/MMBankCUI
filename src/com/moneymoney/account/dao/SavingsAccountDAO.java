package com.moneymoney.account.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.exception.AccountNotFoundException;

public interface SavingsAccountDAO {
	
	SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException;
	SavingsAccount updateAccount(SavingsAccount account);
	SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	boolean deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException;
	void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException;
	Set<SavingsAccount> getSortedAccounts(int choice) throws ClassNotFoundException, SQLException;
	
	
}
