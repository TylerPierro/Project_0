package gringotts.prompts;

import java.util.Scanner;

import gringotts.beans.User;
import gringotts.dao.UserDAO;
import gringotts.dao.UserSerializer;

public class UpdateUserPasswordPrompt implements Prompt {
	private Scanner scan = new Scanner(System.in);
	private UserDAO uDao = new UserSerializer();
	
	//If you fail the balance entry, it should remember your username
	private User rememberMe = null;
	
	@Override
	public Prompt run() {
		//Find out who we're updating
		User userToUpdate = null;
		if (rememberMe == null) {
			System.out.println("Who needs to change their passphrase?");
			String username = scan.nextLine();
			userToUpdate = uDao.retrieveUser(username);
			if (userToUpdate == null)	{
				System.out.println("I don't know them.  They don't bank here.");
				return new AdminMenuPrompt();
			}
			else	rememberMe = userToUpdate;
		} //else userToUpdate = uDao.retrieveUser(username);
		
		System.out.println("What will the new passphrase be?");
		String password = scan.nextLine();
		for (char c : password.toCharArray())
			if (Character.isWhitespace(c))	{
				System.out.println("Our passphrases are only one word.  No spaces!");
				throw new IllegalArgumentException(password);
			}
		
		//Set new password
		userToUpdate.setPassword(password);
		
		uDao.updateUser(userToUpdate);		
		
		System.out.println(userToUpdate.getUsername() + "'s passphrase has been updated!");
		return new AdminMenuPrompt();
	}
}
