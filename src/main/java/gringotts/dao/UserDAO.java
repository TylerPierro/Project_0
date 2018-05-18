package gringotts.dao;

import java.util.List;

import gringotts.beans.User;

public interface UserDAO {
	User retrieveUser(String username);
	void login(User newUser);
	void register(User newUser);
	List<User> retrieveUsers();
	void updateUser(User existingUser);
	void logout();
}
