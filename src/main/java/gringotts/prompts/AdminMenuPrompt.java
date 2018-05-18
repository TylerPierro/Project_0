package gringotts.prompts;

import java.util.Scanner;

import gringotts.dao.AccountDAO;
import gringotts.dao.AccountSerializer;
import gringotts.dao.UserDAO;
import gringotts.dao.UserSerializer;

public class AdminMenuPrompt implements Prompt {
	private Scanner scan = new Scanner(System.in);
	private AccountDAO aDao = new AccountSerializer();
	private UserDAO uDao = new UserSerializer();
	
	@Override
	public Prompt run() {
		System.out.println("Select an option");
		System.out.println("1:  Print all users transaction histories");
		System.out.println("2:  Promote user to admin");
		System.out.println("3:  Demote admin to user");
		System.out.println("4:  Correct user balance");
		//System.out.println("5:  Correct user name");
		System.out.println("5:  Change user password");
		System.out.println("6:  Logout");
		System.out.println("7:  Exit to menu");
		
		String command = scan.nextLine();
		
		switch (command.toLowerCase()) {
		case "1":
			aDao.printAllTransactions();
			return this;
		case "2":
			return new PromoteUpdatePrompt();
		case "3":
			return new DemoteUpdatePrompt();
		case "4":
			return new UpdateUserBalancePrompt();
		/*case "5":
			return new UpdateUsernamePrompt();*/
		case "5":
			return new UpdateUserPasswordPrompt();
		case "6":
			uDao.logout();
			return new MainMenuPrompt();
		case "7": case "exit": case "main": case "main menu":
			return new MainMenuPrompt();
		default:
			System.out.println("We may goblins, but we don't speak gibberish");
			return this;
		}
	}
}
