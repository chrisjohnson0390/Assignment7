package com.meritamerica.main.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="checking_account")
@JsonIgnoreProperties(value = {"accHolder", "transactions"})
public class CheckingAccount extends BankAccount {
	private static double INTEREST_RATE = 0.0001;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id")
	private AccountHolder accHolder;
	
	public AccountHolder getAccHolder() {
		return accHolder;
	}

	public void setAccHolder(AccountHolder accHolder) {
		this.accHolder = accHolder;
	}

	public CheckingAccount(double balance, double interestRate) {
		super(balance, interestRate);
	}
	
	public CheckingAccount(double balance, double interestRate, Date openDate) {
		super(balance, interestRate, openDate);
	}
	
	public CheckingAccount() {
		super(0, INTEREST_RATE);
	}
	
	public CheckingAccount(double balance) {
		super(balance, INTEREST_RATE);
	}
}