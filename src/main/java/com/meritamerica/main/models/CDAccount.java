package com.meritamerica.main.models;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.meritamerica.main.exceptions.FieldErrorException;

@Entity
@JsonIgnoreProperties(value = { "accHolder", "transactions"})
public class CDAccount extends BankAccount {
	private static final double INTEREST = 0.025;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="offer_id")
	private CDOffering offering;
	@ManyToOne
	@JoinColumn(name="id")
	private AccountHolder accHolder;
	
	public CDAccount() throws FieldErrorException {
		super();
		super.setInterestRate(INTEREST);
	}
	
	CDAccount(CDOffering offering, double openingBalance) {
		super(openingBalance, offering.getInterestRate());
		this.offering = offering;
	}	
	
	CDAccount(int term, double openingBalance, double interestRate) {
		this(new CDOffering(term, interestRate), openingBalance);
	}
	
	CDAccount(double openingBalance, double interestRate, Date openDate, int term) {
		super( openingBalance, interestRate, openDate);
		this.offering = new CDOffering(term, interestRate);
	}	
		
	public double futureValue() {
		double futureVal = this.getBalance() * Math.pow(1 + this.getInterestRate(), offering.getTerm());
		return futureVal;
	}
	
	public int getTerm() {
		return offering.getTerm();
	}
	
	public void setTerm(int years) {
		offering.setTerm(years);
	}
	
	public double getInterestRate() {
		return offering.getInterestRate();
	}

	@Override
	public boolean withdraw(double amount) {
		return false;
	}

	@Override
	public boolean deposit(double amount) {
		return false;
	}

	public CDOffering getOffering() {
		return offering;
	}

	public void setOffering(CDOffering offering) {
		if (offering == null)
			return;
		else 
			this.offering = offering;
	}

	public AccountHolder getAccHolder() {
		return accHolder;
	}

	public void setAccHolder(AccountHolder accHolder) {
		this.accHolder = accHolder;
	}
}
