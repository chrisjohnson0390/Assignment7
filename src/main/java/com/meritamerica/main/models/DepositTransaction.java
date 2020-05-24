package com.meritamerica.main.models;

import java.util.Date;

public class DepositTransaction extends Transaction {
	DepositTransaction(BankAccount targetAccount, double amount, Date date) {
		super(targetAccount, amount, date);
	}	
}