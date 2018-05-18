package gringotts.prompts;

import java.util.Scanner;

import gringotts.beans.User;
import gringotts.dao.UserDAO;
import gringotts.dao.UserSerializer;

public class DemoteUpdatePrompt implements Prompt {
	private Scanner scan = new Scanner(System.in);
	private UserDAO uDao = new UserSerializer();
	
	@Override
	public Prompt run() {
		//Find out who we're updating
		System.out.println("Who is leaving the Gringotts team?");
		String username = scan.nextLine();
		User userToUpdate = uDao.retrieveUser(username);
		if (userToUpdate == null)	{
			System.out.println("I don't know them.  They don't bank here.");
			return new AdminMenuPrompt();
		}
		
		//Find out is user is already not an admin
		if (!userToUpdate.isAdmin())	{
			System.out.println("You should know your customers better.  They're just a user.");
			return new AdminMenuPrompt();
		}
		
		//Set admin privileges to true
		userToUpdate.setAdmin(false);
		
		uDao.updateUser(userToUpdate);		
		
		System.out.println(userToUpdate.getUsername() + " is no longer an administrator!");
		return new AdminMenuPrompt();
	}
}
