package gringotts.beans;

import java.io.Serializable;
import java.util.ArrayList;

import gringotts.dao.AccountDAO;
import gringotts.dao.AccountSerializer;

//Default account name is Checking (everyone has a checking account)
//Therefore, default account filename is username + "Checking.txt"
public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8543606228541109743L;

	private transient AccountDAO aDao = new AccountSerializer();

	private double balance;
	private String accountName;
	private ArrayList<Transaction> accountTransactions;

	public Account() {
		super();
		this.balance = 0;
		this.accountName = "Checking";
	}

	public Account(double balance, String accountName, ArrayList<Transaction> accountTransactions) {
		super();
		this.balance = balance;
		this.accountName = accountName;
		this.accountTransactions = accountTransactions;
	}



	public Account(double balance, String accountName) {
		super();
		this.balance = balance;
		this.accountName = accountName;
		this.accountTransactions = new ArrayList<Transaction>();
	}

	public Account(String accountName) {
		super();
		this.balance = 0d;
		this.accountName = accountName;
		this.accountTransactions = new ArrayList<Transaction>();
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public ArrayList<Transaction> getAccountTransactions(User currentUser) {
		//This way the account never actually has to store transaction history, and it reduces the size of the object
		try	{
			return (ArrayList<Transaction>) aDao.getTransactionHistory(currentUser);
		} catch (NullPointerException e)	{
			return null;
		}
	}

	public void setAccountTransactions(ArrayList<Transaction> accountTransactions) {
		this.accountTransactions = accountTransactions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + ((accountTransactions == null) ? 0 : accountTransactions.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (accountTransactions == null) {
			if (other.accountTransactions != null)
				return false;
		} else if (!accountTransactions.equals(other.accountTransactions))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + ", accountName=" + accountName + "]";
	}
}
