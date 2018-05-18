package gringotts.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import gringotts.beans.User;

public class UserSerializer implements UserDAO {
	private final String FILE_LOCATION = "src/main/resources/";
	
	@Override
	public User retrieveUser(String username)	{
		List<User> userList = retrieveUsers();
		for (User test : userList)	{
			if (test.getUsername().equals(username))	return test;
		}
		return null;
	}
	
	@Override
	public void login(User existingUser) {
		//more accurate isAlreadyRegistered, but that's too long
		boolean isRegistered = false;
		boolean passwordVerified = false;
		
		//Populate all of the users
		List<User> userList = retrieveUsers();
		if (userList == null) userList = new ArrayList<User>();

		//Check to make sure the name is not already on the list
		//If user is already included, do not add duplicates.  This checks on name, not on User object
		try	(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_LOCATION + "currentUser.txt"))) {
			for (User test : userList)	{
				if (test.getUsername().equals(existingUser.getUsername()))	{
					isRegistered = true;
					if (test.getPassword().equals(existingUser.getPassword())) passwordVerified = true;
					System.out.println("Welcome back " + existingUser.getUsername());
					break;
				}
			}
			
			//They can't log in if they haven't registered
			if (!isRegistered) 	throw new NullPointerException(); 
		
			//Make sure they enter the right password
			if (passwordVerified)	{
				//Login user (write user to currentUser file)
				oos.writeObject(existingUser);
				oos.flush();
				System.out.println("We've got you logged in on the books, now follow me to your vault.");
			}
			else throw new IllegalAccessError();
		} catch (IllegalAccessError e)	{
			System.out.println("You almost fooled me with that polyjuice potion.  The real " + existingUser.getUsername() + " would never be so careless!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e)	{
			System.out.println("You haven't registered with us.  You should though, we offer the safest vaults on Earth.");
		}
	}

	@Override
	public void register(User newUser) {		
		//more accurate isAlreadyRegistered, but that's too long
		boolean isRegistered = false;
		
		//Populate all of the users
		List<User> userList = retrieveUsers();
		if (userList == null) userList = new ArrayList<User>();

		//Check to make sure the name is not already on the list
		//If user is already included, do not add duplicates.  This checks on name, not on User object
		for (User test : userList)	{
			if (test.getUsername().equals(newUser.getUsername()))	{
				isRegistered = true;
				System.out.println("You're already registered here");
				break;
			}
		}
		
		//Add if not already on database
		if (!isRegistered) 	{
			System.out.println("adding new user");
			userList.add(newUser);
		}
		System.out.println("user list: " + userList);
		// write new list to file
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_LOCATION + "users.txt"))) {
			oos.writeObject(userList);
			oos.flush();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> retrieveUsers() {
		try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(FILE_LOCATION + "users.txt"))) {
			return (List<User>) inStream.readObject();
		} catch (EOFException e)	{
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
		} 
		
		return null;
	}

	@Override
	public void updateUser(User existingUser) {
		//System.out.println(existingUser.getUserAccount());
		ArrayList<User> userList = (ArrayList<User>) retrieveUsers();
		boolean found = false;
		for (int i=0; i<userList.size(); i++) {
			if (userList.get(i).getUsername().equals(existingUser.getUsername()))	{
				userList.set(i, existingUser);
				found = true;
				break;
			}
		}
		
		//System.out.println(userList);
		//Update userList
		if (!found) throw new IndexOutOfBoundsException();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_LOCATION + "users.txt"))) {
			oos.writeObject(userList);
			oos.flush();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
		
		//Update currentUser
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_LOCATION + "currentUser.txt"))) {
			oos.writeObject(existingUser);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void logout() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_LOCATION + "currentUser.txt"))) {
			User nullUser = null;
			oos.writeObject(nullUser);
			oos.flush();
			System.out.println("We'll see you next time");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
