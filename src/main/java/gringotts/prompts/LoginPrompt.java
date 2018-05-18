package gringotts.prompts;

import java.util.Scanner;

import gringotts.beans.User;
import gringotts.dao.UserDAO;
import gringotts.dao.UserSerializer;

public class LoginPrompt implements Prompt {
	Scanner scan = new Scanner(System.in);
	UserDAO uDao = new UserSerializer();
	
	@Override
	public Prompt run() {
		System.out.println("Oh hello again... I'm sorry my memory is failing me. What was your name again?");
		String username = scan.nextLine();
		for (char c : username.toCharArray()) 
			if (!(Character.isAlphabetic(c) || Character.isWhitespace(c)))	{
				System.out.println("You know we don't call you that!  Try again.");
				throw new IllegalArgumentException(username);
			}
		System.out.println("Oh I remember now, " + username + ".\nTell me your pass phrase. Remember, our magic is case sensitive.");
		String password = scan.nextLine();
		for (char c : password.toCharArray())
			if (Character.isWhitespace(c))	{
				System.out.println("Our passphrases are only one word.  No spaces!");
				throw new IllegalArgumentException(password);
			}
		
		User newUser = uDao.retrieveUser(username);
		uDao.login(newUser);
		//System.out.println("Gringotts is happy to have you, " + username);
		return new MainMenuPrompt();
	}

}
