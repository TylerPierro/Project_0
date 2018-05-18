package gringotts.prompts;

import java.util.Scanner;

import gringotts.beans.Transaction;
import gringotts.beans.User;
import gringotts.dao.AccountDAO;
import gringotts.dao.AccountSerializer;

//Needs to pass a Transaction withdrawl
public class WithdrawPrompt implements Prompt {
	private Scanner scan = new Scanner(System.in);
	private AccountDAO aDao = new AccountSerializer();
	//You have to be logged in first.  If you aren't, currentUser.txt will be null
	protected User username = aDao.getCurrentUser();
	//Transaction test = new Transaction(amount, date, description)
	
	@Override
	public Prompt run() {
		System.out.println("How much gold are you retrieving from your vault?");
		Double amount = 0d;
		try	{
			amount = Double.parseDouble(scan.nextLine());
		} catch (NumberFormatException e)	{
			System.out.println("Tell me a number.  Digits only.");
			return this;
		}
		if (amount < 0) {
			System.out.println("You know you can't retrieve negative gold.  Why do I bother with you?");
			return this;
		}
		
		System.out.println("Describe what the withdrawl is for, for our records");
		String description = "";
		if (scan.hasNextLine()) description = scan.nextLine();
		if (description.equals("")) description = "Unspecified, shady business";
		
		//Call constructor based on null fields
		Transaction withdrawl;
		//If no description
		if (description.equals("") || description == null)
			withdrawl = new Transaction(amount);
		//Should never get a null date
		if (amount == 0 || amount == null)	{
			withdrawl = new Transaction();
			System.out.println("Did you just come to say hello?");
		}
		else withdrawl = new Transaction(amount, description);
		
		System.out.println(withdrawl.toString());
		aDao.withdraw(withdrawl);
		return new MainMenuPrompt();
	}

}
