package gringotts.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable, Comparable<Transaction> {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8083680691197788752L;
	
	private double amount;
	private LocalDateTime date;
	private String description;
	
	public Transaction() {
		super();
		this.amount = 0;
		this.date = LocalDateTime.now();
		this.description = "Unspecified, shady business";
	}

	public Transaction(double amount, LocalDateTime date, String description) {
		super();
		this.amount = amount;
		this.date = date;
		if (description.equals("")) this.description = "Unspecified, shady business";
		else this.description = description;
	}

	public Transaction(double amount, LocalDateTime date) {
		super();
		this.amount = amount;
		this.date = date;
		this.setDescription("Unspecified, shady business");
	}

	public Transaction(double amount, String description) {
		super();
		this.amount = amount;
		if (description.equals("")) this.description = "Unspecified, shady business";
		this.description = description;
		this.setDate(LocalDateTime.now());
	}

	public Transaction(double amount) {
		super();
		this.amount = amount;
		this.setDescription("Unspecified, shady business");
		this.setDate(LocalDateTime.now());
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", date=" + date + ", description=" + description + "]\n";
	}

	/*@Override
	public int compare(Transaction arg0, Transaction arg1) {
		if (arg0.getDate().compareTo(arg1.getDate()) > 0) return 1;
		else if (this.getDate().equals(arg1.getDate())) return 0;
		else return -1;
	}*/

	public int compareTo(Transaction that) {
		if (this.getDate().compareTo(that.getDate()) > 0) return 1;
		else if (this.getDate().equals(that.getDate())) return 0;
		else return -1;
	}
}