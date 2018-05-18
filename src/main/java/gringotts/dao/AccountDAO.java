package gringotts.dao;

import java.util.List;

import gringotts.beans.Transaction;
import gringotts.beans.User;

public interface AccountDAO {
	User getCurrentUser();
	List<Transaction> getTransactionHistory();
	List<Transaction> getTransactionHistory(User adminPriviledge);
	void updateTransactionHistory(Transaction toAdd);
	void deposit(Transaction drop);
	void withdraw(Transaction pickup);
	double getBalance();
	void printAllTransactions();
}