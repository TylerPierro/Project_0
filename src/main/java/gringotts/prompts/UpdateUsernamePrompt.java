package gringotts.prompts;

import java.util.Scanner;

import gringotts.beans.User;
import gringotts.dao.UserDAO;
import gringotts.dao.UserSerializer;

public class UpdateUsernamePrompt implements Prompt {
	private Scanner scan = new Scanner(System.in);
	private UserDAO uDao = new UserSerializer();
	
	//If you fail the balance entry, it should remember your username
	private User rememberMe = null;
	
	@Override
	public Prompt run() {
		//Find out who we're updating
		User userToUpdate = null;
		if (rememberMe == null) {
			System.out.println("Who changed their name?");
			String username = scan.nextLine();
			userToUpdate = uDao.retrieveUser(username);
			if (userToUpdate == null)	{
				System.out.println("I don't know them.  They don't bank here.");
				return new AdminMenuPrompt();
			}
			else	rememberMe = userToUpdate;
		} //else userToUpdate = uDao.retrieveUser(username);
		
		System.out.println("What would they prefer to be called?");
		String newUsername = scan.nextLine();
		for (char c : newUsername.toCharArray()) {
			if (!(Character.isAlphabetic(c) || Character.isWhitespace(c)))	{
				System.out.println("Please enter a name.  Alphabetic characters only!");
				return this;
			}
		}
		
		//Set new username
		//User oldUser = userToUpdate;
		userToUpdate.setUsername(newUsername);
		
		uDao.updateUser(userToUpdate);		
		
		//System.out.println(oldUsername + " will now be called " + userToUpdate.getUsername());
		return new AdminMenuPrompt();
	}
}
