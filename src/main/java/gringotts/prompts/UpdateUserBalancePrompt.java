package gringotts.prompts;

import java.util.Scanner;

import gringotts.beans.Account;
import gringotts.beans.User;
import gringotts.dao.UserDAO;
import gringotts.dao.UserSerializer;

public class UpdateUserBalancePrompt implements Prompt {
	private Scanner scan = new Scanner(System.in);
	private UserDAO uDao = new UserSerializer();
	
	//If you fail the balance entry, it should remember your username
	private User rememberMe = null;
	
	@Override
	public Prompt run() {
		//Find out who we're updating
		User userToUpdate = null;
		if (rememberMe == null) {
			System.out.println("Whose balance needs to be updated?");
			String username = scan.nextLine();
			userToUpdate = uDao.retrieveUser(username);
			if (userToUpdate == null)	{
				System.out.println("I don't know them.  They don't bank here.");
				return new AdminMenuPrompt();
			}
			else	rememberMe = userToUpdate;
		} //else userToUpdate = uDao.retrieveUser(username);
		
		System.out.println("What should their balance be?");
		String lineEntry = scan.nextLine();
		for (char c : lineEntry.toCharArray()) {
			if (!Character.isDigit(c))	{
				System.out.println("Please enter a number.  Digits only!");
				return this;
			}
		}
		double newBalance = Integer.parseInt(lineEntry);
		if (newBalance < 0)	{
			System.out.println("Are you sure you want to enter a negative balance?");
			while (true)	{
				String response = scan.nextLine();
				if (response.toLowerCase().equals("yes"))	{
					System.out.println("If you say so");
					break;
				}
				else if (response.toLowerCase().equals("no"))	{
					System.out.println("Then try again");
					return this;
				}
				else System.out.println("You need to be sure.  Yes or no?");
			}
		}
		
		//Set new user balance
		Account userAccount = userToUpdate.getUserAccount();
		userAccount.setBalance(newBalance);
		userToUpdate.setUserAccount(userAccount);
		
		uDao.updateUser(userToUpdate);		
		
		System.out.println(userToUpdate.getUsername() + " now has balance: " + userToUpdate.getUserAccount().getBalance());
		return new AdminMenuPrompt();
	}
}
