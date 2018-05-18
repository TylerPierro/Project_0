package gringotts.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gringotts.beans.Account;
import gringotts.beans.Transaction;
import gringotts.beans.User;

public class AccountSerializer implements AccountDAO {
	private final String FILE_LOCATION = "src/main/resources/";
	//The filename should be username + account name + "Transactions.txt"
	private UserDAO uDao = new UserSerializer();
	
	@Override
	public User getCurrentUser() {
		User loggedUser = null;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_LOCATION + "currentUser.txt"))){
			loggedUser = (User) ois.readObject();
			
			//Any account access operations require a user to be logged in
			if (loggedUser == null)	{
				throw new IllegalAccessError();
			}
			return loggedUser;
		} catch (EOFException e) {
			System.out.println("Log in first");
			return null;
		}
		catch (IllegalAccessError e)	{
			return null;
		} catch (FileNotFoundException e) {
			return null;
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loggedUser;
	}
	
	//If I want to make multiple accounts I need to add Account acc in get&updateTransactionHistory() to parameters

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> getTransactionHistory() {
		User loggedUser = getCurrentUser();
		try	{
			if (loggedUser == null) throw new IllegalAccessError();
			Account acc = loggedUser.getUserAccount();
			String filename = loggedUser.getUsername() + acc.getAccountName() + "Transactions.txt";
			
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_LOCATION + filename))){
				System.out.println(filename);
				List<Transaction> userHistory =  (List<Transaction>) ois.readObject();
				return userHistory;
			} catch (EOFException e) {
				System.out.println("No transactions for this account yet\n");
				//e.printStackTrace();
			} catch (FileNotFoundException e) {
				return new ArrayList<Transaction>();
				//e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} catch (IllegalAccessError e)	{
			System.out.println("Please log in first");
			return new ArrayList<Transaction>();
		} 
		return new ArrayList<Transaction>();
	}
	
	@Override
	public void updateTransactionHistory(Transaction toAdd) {
		//Transactions should be checked for validity in TransactionPrompt
		
		User loggedUser = getCurrentUser();
		try	{
			if (loggedUser == null) throw new IllegalAccessError();
		
			Account acc = loggedUser.getUserAccount();
			String filename = loggedUser.getUsername() + acc.getAccountName() + "Transactions.txt";
			
			List<Transaction> updateList = getTransactionHistory();
			updateList.add(toAdd);
			Collections.sort(updateList);
			
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_LOCATION + filename))) {
				oos.writeObject(updateList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IllegalAccessError e)	{
			System.out.println("Please log in first");
		}
	}

	@SuppressWarnings("unused")
	@Override
	public void deposit(Transaction drop) {
		User loggedUser = getCurrentUser();
		double balance = loggedUser.getUserAccount().getBalance();
		double transValue = drop.getAmount();
		System.out.println("Balance = " + balance + "\nAmount = " + transValue);
		try	{
			if (loggedUser == null) throw new IllegalAccessError();
			loggedUser.getUserAccount().setBalance(balance+transValue);
			uDao.updateUser(loggedUser);
			updateTransactionHistory(drop);
		} catch (IllegalArgumentException e)	{
		} catch (IllegalAccessError e)	{
			System.out.println("Please log in first");
		}
	}

	@SuppressWarnings("unused")
	@Override
	public void withdraw(Transaction pickup) {
		User loggedUser = getCurrentUser();
		double balance = loggedUser.getUserAccount().getBalance();
		double transValue = pickup.getAmount();
		try	{
			if (loggedUser == null) throw new IllegalAccessError();
			if (balance - transValue < 0)	{
				System.out.println("You don't have that much money to withdraw.\nGringott's doesn't allow overdrafting.");
				System.out.println(balance + " - " + transValue);
				throw new IllegalArgumentException();
			}
			else {
				loggedUser.getUserAccount().setBalance(balance-transValue);
				uDao.updateUser(loggedUser);
				updateTransactionHistory(pickup);
			}
		} catch (IllegalArgumentException e)	{
		} catch (IllegalAccessError e)	{
			System.out.println("Please log in first");
		}
	}

	@Override
	public double getBalance() {
		User loggedUser = getCurrentUser();
		try	{
			if (loggedUser == null) throw new IllegalAccessError();
		} catch (IllegalAccessError e)	{
			System.out.println("Please log in first");
			return -0.1;
		}
		return loggedUser.getUserAccount().getBalance();
	}

	public List<Transaction> getTransactionHistory(User adminPriviledge)	{
		try	{
			if (adminPriviledge == null) throw new IllegalAccessError();
			Account acc = adminPriviledge.getUserAccount();
			String filename = adminPriviledge.getUsername() + acc.getAccountName() + "Transactions.txt";
			
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_LOCATION + filename))){
				@SuppressWarnings("unchecked")
				List<Transaction> userHistory =  (List<Transaction>) ois.readObject();
				return userHistory;
			} catch (EOFException e) {
				System.out.println("No transactions for this account yet\n");
				//e.printStackTrace();
			} catch (FileNotFoundException e) {
				return new ArrayList<Transaction>();
				//e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} catch (IllegalAccessError e)	{
			System.out.println("Please log in first");
			return new ArrayList<Transaction>();
		} 
		return new ArrayList<Transaction>();
	}
	
	@Override
	public void printAllTransactions() {
		ArrayList<User> userList = (ArrayList<User>) uDao.retrieveUsers();
		for (User bankUser : userList)	{
			//Print every user
			System.out.println(bankUser.getUsername() + ", " + bankUser.getUserAccount().getAccountName());
			//This only prints the currentUser's transactions, need to be able to pass a user object
			ArrayList<Transaction> userTrans = (ArrayList<Transaction>) getTransactionHistory(bankUser);
			//If that user has transactions print them, else do nothing
			if (/*userTrans != new ArrayList<Transaction>()*/ userTrans.size() != 0) System.out.println(userTrans + "\n");
			//else if (userTrans == null)
		}
	}

}
