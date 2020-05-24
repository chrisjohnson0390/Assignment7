package com.meritamerica.main.models;

import java.util.Date;

public class TransferTransaction extends Transaction {
	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount, Date date) {
		super(sourceAccount, targetAccount, amount, date);
	}
}
