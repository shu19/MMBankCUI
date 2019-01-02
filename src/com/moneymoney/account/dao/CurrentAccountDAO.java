package com.moneymoney.account.dao;

import java.sql.SQLException;
import java.util.List;

import com.moneymoney.account.CurrentAccount;
import com.moneymoney.exception.AccountNotFoundException;

public interface CurrentAccountDAO {
	
	CurrentAccount createNewAccount(CurrentAccount account) throws ClassNotFoundException, SQLException;
//	CurrentAccount updateAccount(CurrentAccount account);
	CurrentAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	boolean deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	List<CurrentAccount> getAllCurrentAccount() throws ClassNotFoundException, SQLException;
	void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException;
	List<CurrentAccount> getSortedAccounts(int choice) throws ClassNotFoundException, SQLException;
	int updateAccount(int accountnumber, String newAccountHolderName) throws ClassNotFoundException, SQLException;
	double getAccountBalance(int accountnumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	CurrentAccount getAccountByHolderName(String accountHolderName) throws AccountNotFoundException, ClassNotFoundException, SQLException;
	List<CurrentAccount> getAllCurrentAccountInBalanceRange(
			double minimumAccountBalance, double maximumAccountBalance) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	
	
}
