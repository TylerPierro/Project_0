package gringotts.beans;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8114079389482875172L;
	
	private String username;
	private String password;
	private Account userAccount;
	private boolean isAdmin;
	
	/*public User() {
		super();
	}*/
	
	//No one should have to enter their list of accounts to create a User object
	/*public User(String username, String password, ArrayList<Account> userAccounts, boolean isAdmin) {
		super();
		this.username = username;
		this.password = password;
		this.userAccounts = userAccounts;
		this.isAdmin = isAdmin;
	}*/


	public User(String username, String password, boolean isAdmin) {
		super();
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.userAccount = new Account();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.isAdmin = false;
		this.userAccount = new Account();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(Account userAccount) {
		this.userAccount = userAccount;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isAdmin ? 1231 : 1237);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userAccount == null) ? 0 : userAccount.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (isAdmin != other.isAdmin)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userAccount == null) {
			if (other.userAccount != null)
				return false;
		} else if (!userAccount.equals(other.userAccount))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	//Doesn't reveal password or account information
	@Override
	public String toString() {
		return "User [username=" + username + ", isAdmin=" + isAdmin + " account: " + userAccount.toString() + "]";
	}
}
