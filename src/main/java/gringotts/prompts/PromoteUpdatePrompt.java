package gringotts.prompts;

import java.util.Scanner;

import gringotts.beans.User;
import gringotts.dao.UserDAO;
import gringotts.dao.UserSerializer;

public class PromoteUpdatePrompt implements Prompt {
	private Scanner scan = new Scanner(System.in);
	private UserDAO uDao = new UserSerializer();
	
	@Override
	public Prompt run() {
		//Find out who we're updating
		System.out.println("Who is joining the Gringotts team?");
		String username = scan.nextLine();
		User userToUpdate = uDao.retrieveUser(username);
		if (userToUpdate == null)	{
			System.out.println("I don't know them.  They don't bank here.");
			return new AdminMenuPrompt();
		}
		
		//Find out if already user
		if (userToUpdate.isAdmin())	{
			System.out.println("You should know your coworkers better.  They're already an admin.");
			return new AdminMenuPrompt();
		}
		
		//Set admin privileges to true
		userToUpdate.setAdmin(true);
		
		uDao.updateUser(userToUpdate);		
		
		System.out.println(userToUpdate.getUsername() + " is now an administrator.  Welcome aboard!");
		return new AdminMenuPrompt();
	}

}
