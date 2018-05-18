package gringotts.launcher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import gringotts.beans.User;
import gringotts.prompts.MainMenuPrompt;
import gringotts.prompts.Prompt;

public class Home	{
	//public static User user;
	public final static String FILE_LOCATION = "src/main/resources/";
	
    public static void main(String[] args)	{
    	
    	System.out.println("Welcome to Gringott's Bank\nThe safest place on Earth");
    	Prompt currentPrompt = new MainMenuPrompt();
    	while(true)	{
        	currentPrompt = currentPrompt.run();
        }
    }
}
