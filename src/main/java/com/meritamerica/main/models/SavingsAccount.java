package com.meritamerica.main.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "accHolder", "transactions"  })
public class SavingsAccount extends BankAccount {
	
	@ManyToOne
	@JoinColumn(name="id")
	private AccountHolder accHolder;
	
	private static double INTEREST_RATE = 0.01;
	SavingsAccount(double balance, double interestRate){
		super(balance, interestRate);    
     }
	
	SavingsAccount( double balance, double interestRate, Date openDate){
		super( balance, interestRate, openDate);    
     }
	
	SavingsAccount(double balance) {
		super(balance, INTEREST_RATE);
	}
	
	public SavingsAccount() {
		super(0, INTEREST_RATE);
	}

	public AccountHolder getAccHolder() {
		return accHolder;
	}

	public void setAccHolder(AccountHolder accHolder) {
		this.accHolder = accHolder;
	}
}