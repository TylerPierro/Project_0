package gringotts.prompts;

import java.util.Scanner;

import gringotts.beans.Transaction;
import gringotts.beans.User;
import gringotts.dao.AccountDAO;
import gringotts.dao.AccountSerializer;

public class DepositPrompt implements Prompt {
	private Scanner scan = new Scanner(System.in);
	private AccountDAO aDao = new AccountSerializer();
	//You have to be logged in first.  If you aren't, currentUser.txt will be null
	protected User username = aDao.getCurrentUser();
	//Transaction test = new Transaction(amount, date, description)
	
	@Override
	public Prompt run() {
		System.out.println("How much gold are you depositing today?");
		Double amount = 0d;
		try {
			amount = Double.parseDouble(scan.nextLine());
		} catch (NumberFormatException e)	{
			System.out.println("Tell me a number.  Digits only");
			return this;
		}
		if (amount < 0) {
			System.out.println("You know you can't deposit negative gold.  Why do I bother with you?");
			return this;
		}
		
		System.out.println("Describe how you came by this windfall");
		String description = "";
		if (scan.hasNextLine()) description = scan.nextLine();
		if (description.equals("")) description = "Unspecified, shady business";
		
		//Call constructor based on null fields
		Transaction deposit;
		//If no description
		if (description.equals("") || description == null)
			deposit = new Transaction(amount);
		if (amount == 0 || amount == null)	{
			deposit = new Transaction();
			System.out.println("Did you just come to say hello?");
		}
		else deposit = new Transaction(amount, description);
		
		System.out.println(deposit.toString());
		aDao.deposit(deposit);
		return new MainMenuPrompt();
	}
}
