package com.meritamerica.main.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.meritamerica.main.exceptions.FieldErrorException;

@MappedSuperclass
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="account_number")
	protected long accountNumber;
	@Positive
	private double balance;
	@NotNull
	@Positive
	private double interestRate;
	private Date openDate;
	@Transient
	private List<Transaction> transactions;

	public BankAccount() {
		this.transactions = new ArrayList<>();
		this.openDate = new Date();
	}
	
	BankAccount(double balance, double interestRate) {
		this(balance, interestRate, new Date());
	}
	
	BankAccount( double balance, double interestRate, Date accountOpenedOn) {
		this.balance = balance;
		this.interestRate = interestRate;
		this.openDate = accountOpenedOn;
		transactions = new ArrayList<>();
	}
	
	public boolean withdraw(double amount) {
		if (amount <= 0) {
			System.out.println("The amount needs to be more than 0");
			return false;
		} else if (amount > this.balance) {
			System.out.println("The amount need to be smaller or equal to the balance");
			return false;
		} else {
			this.balance -= amount;
			return true;
		}
	}
	
	public boolean deposit(double amount) {
		if (amount <= 0) {
			System.out.println("The deposit amount needs to be larger than 0");
			return false;
		} else {
			this.balance += amount;
			return true;
		}
	}
	
	public double futureValue(int years) {
		double futureVal = this.balance * Math.pow(1 + getInterestRate(), years);
		return futureVal;
	}
	
	public void addTransaction(Transaction tran){
		System.out.println("Transaction thing");
		System.out.println(tran);
		transactions.add(tran);
	}
	
	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public long getAccountNumber() {
		return this.accountNumber;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public double getInterestRate() {
		return this.interestRate;
	}
	
	public Date getOpenedOn() {
		return this.openDate;
	}
	
	public void setBalance(double balance){
		this.balance = balance;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setInterestRate(double interestRate) throws FieldErrorException {
		this.interestRate = interestRate;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
