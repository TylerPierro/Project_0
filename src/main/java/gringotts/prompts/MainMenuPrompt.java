package gringotts.prompts;

import java.util.ArrayList;
import java.util.Scanner;

import gringotts.beans.Transaction;
import gringotts.beans.User;
import gringotts.dao.AccountDAO;
import gringotts.dao.AccountSerializer;
import gringotts.dao.UserDAO;
import gringotts.dao.UserSerializer;

public class MainMenuPrompt implements Prompt {
	private Scanner scan = new Scanner(System.in);
	private AccountDAO aDao = new AccountSerializer();
	private UserDAO uDao = new UserSerializer();
	
	@Override
	public Prompt run() {
		boolean isLoggedIn = false;
		User test = aDao.getCurrentUser();
		if (test != null) isLoggedIn = true;
		System.out.println("What would you like to do today?");
		System.out.println("1:  Register new user");
		System.out.println("2:  Log in existing user");
		System.out.println("3:  Withdraw");
		System.out.println("4:  Deposit");
		System.out.println("5:  View balance");
		System.out.println("6:  View transaction history");
		System.out.println("7:  Logout");
		System.out.println("8:  **Admins only** Administrator menu");
		
		try	{
			String command = scan.nextLine();	
	    	
	    	//First word of input determines action
	    	switch (command.toLowerCase()) {
	    	case "1": case "register":
	    		return new RegisterPrompt();
	    	case "2": case "login": case "log in":
	    		return new LoginPrompt();
	    	case "3": case "withdraw":
	    		if (isLoggedIn) return new WithdrawPrompt();
	    		else	{
	    			System.out.println("Please log in first");
	    			return this;
	    		}
	    	case "4": case "deposit":
	    		if (isLoggedIn) return new DepositPrompt();
	    		else	{
	    			System.out.println("Please log in first");
	    			return this;
	    		}
	    	case "5": case "view balance": case "balance":
	    		double balance = aDao.getBalance();
	    		if (balance == -0.1) break; 
	    		else System.out.println("Looking in your vault, I can tell you have " + balance + " gold.");
	    		return this;
	    	case "6": case "transactions": case "history": case "transaction history":
	    		if (isLoggedIn)	{
		    		ArrayList<Transaction> history = (ArrayList<Transaction>) aDao.getTransactionHistory();
		    		//Collections.sort(history);
		    		System.out.println(history);
	    		} else	System.out.println("Please log in first");
	    		return this;
	    	case "7": case "logout": case "log out":
	    		uDao.logout();
	    		return this;
	    	case "8": case "admin":
	    		//Logic to check that the user is an admin before accessing the admin only menu
	    		User cU = aDao.getCurrentUser();
	    		if (cU.isAdmin())	return new AdminMenuPrompt();
	    		else	{
	    			System.out.println("So you want to be an admin?  Tell me the admin priviledge phrase");
	    			String response = scan.nextLine();
	    			if (response.equals("Solemnly Swear"))	{
	    				System.out.println("Oh well as long as your Sirius about it.  You're an admin, " + cU.getUsername());
	    				cU.setAdmin(true);
	    				uDao.updateUser(cU);
	    				return new AdminMenuPrompt();
	    			}
	    			else 	{
	    				System.out.println("You don't have authority to access that part of the bank");
	    				return this;
	    			}
	    		}
	    	case "":
	    		System.out.println("We're waiting");
	    		return this;
			default:
				System.out.println("We may goblins, but we don't speak gibberish");
				return this;
			}
		} catch (NullPointerException e)	{
			return this;
		}
		return this;
	}

}
