package gringotts.prompts;

import java.util.Scanner;

import gringotts.beans.User;
import gringotts.dao.UserDAO;
import gringotts.dao.UserSerializer;

public class RegisterPrompt implements Prompt {
	Scanner scan = new Scanner(System.in);
	UserDAO uDao = new UserSerializer();
	
	@Override
	public Prompt run() {
		try	{
			System.out.println("Name, please.");
			String username = scan.nextLine();
			for (char c : username.toCharArray()) 
				if (!(Character.isAlphabetic(c) || Character.isWhitespace(c)))	{
					System.out.println("I'm not going to call you that!  Try again.");
					throw new IllegalArgumentException(username);
				}
			System.out.println("And what will be your new pass phrase " + username + "?");
			String password = scan.nextLine();
			for (char c : password.toCharArray())
				if (Character.isWhitespace(c))	{
					System.out.println("Our passphrases are only one word.  No spaces!");
					throw new IllegalArgumentException(password);
				}
			
			User newUser = new User(username, password);
			uDao.register(newUser);
			System.out.println("Gringotts is happy to have you, " + username);
		} catch (NumberFormatException e)	{
			System.out.println("Invalid input, try again");
			return this;
		} catch (IllegalArgumentException e1)	{
			//System.out.println("Invalid input, try again");
			return this;
		}
		
		return new MainMenuPrompt();
	}

}
