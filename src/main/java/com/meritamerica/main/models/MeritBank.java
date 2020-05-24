package com.meritamerica.main.models;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import com.meritamerica.main.exceptions.*;

public class MeritBank {
	private static AccountHolder[] accountHolders = new AccountHolder[10];
	private static CDOffering[] CDOfferings = new CDOffering[0];
	private static int numbOfAccountHolder = 0;
	public static FraudQueue fraudQueue = new FraudQueue();
	
	public static AccountHolder addAccountHolder(AccountHolder accountHolder) {
		MeritBank.numbOfAccountHolder++;
		if (MeritBank.numbOfAccountHolder >= MeritBank.accountHolders.length) {
			AccountHolder[] accounts = Arrays.copyOf(MeritBank.accountHolders, MeritBank.accountHolders.length * 2);
			MeritBank.accountHolders = accounts;
		}
		MeritBank.accountHolders[MeritBank.numbOfAccountHolder - 1] = accountHolder;	
		return accountHolder;
	}
	
	public static void addCDOffering(CDOffering offering) {
		CDOffering[] offerings = Arrays.copyOf(MeritBank.CDOfferings, MeritBank.CDOfferings.length + 1);
		offerings[offerings.length - 1] = offering;
		MeritBank.CDOfferings = offerings;
	}
	
	public static AccountHolder getAccountHolder(long id) {
		for (AccountHolder account : MeritBank.accountHolders) {
			if (account == null) {
				return null;
			}
			if (account.getId() == id) {
				return account;
			}
		}
		return null;
	}
	
	public static BankAccount findAccount(long ID) {
		if (accountHolders != null) {
			for (int i = 0; i < accountHolders.length; i++) {
				if (accountHolders[i] == null) {
					break;
				}
				BankAccount acc = accountHolders[i].findAccount(ID);
				if (acc != null) {
					return acc;
				}
			}
		}
		return null;
	}
	
	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(date);
	}
	
	public static String decimalFormat(double numb) {
		DecimalFormat df = new DecimalFormat("#.####");
		return df.format(numb);
	}
	
	public static String formatNumber(double d) {
	    if(d == (int) d)
	        return String.format("%d",(int)d);
	    else
	        return String.format("%s",d);
	} 

	public static AccountHolder[] sortAccountHolders() {
		AccountHolder[] accountHolder = MeritBank.accountHolders;
		int n = accountHolder.length; 
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) 
                if (accountHolder[j].compareTo(accountHolder[j+1]) > 0) {  
                    AccountHolder temp = accountHolder[j]; 
                    accountHolder[j] = accountHolder[j+1]; 
                    accountHolder[j+1] = temp; 
                } 
        }
        return accountHolder;
	}
	
	public static AccountHolder[] getAccountHolders() {
		AccountHolder[] accounts = Arrays.copyOf(MeritBank.accountHolders, MeritBank.numbOfAccountHolder);
		return accounts;
	}
	
	public static CDOffering[] getCDOfferings() {
		return CDOfferings;
	}
	
	public static CDOffering getBestCDOffering(double depositAmount) {
		double highestYield = 0;
		double tempYield = 0;
		int bestIndex = 0; 
		if (MeritBank.CDOfferings != null) {
			for (int i=0; i < MeritBank.CDOfferings.length; i++) {
				tempYield = MeritBank.futureValue(depositAmount, CDOfferings[i].getInterestRate(), CDOfferings[i].getTerm());
				if (tempYield > highestYield) {
					highestYield = tempYield;
					bestIndex = i;
				}
			}
			return CDOfferings[bestIndex];
		} else {
			return null;
		}
	}
	
	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		double highestYield = 0;
		int secondBestI = 0;
		int bestI = 0;
		double secondBestYield = 0;
		double tempYield = 0;
		if (MeritBank.CDOfferings != null) {
			for (int i=0; i < MeritBank.CDOfferings.length; i++) {
				tempYield = MeritBank.futureValue(depositAmount, CDOfferings[i].getInterestRate(), CDOfferings[i].getTerm());
				if (tempYield > highestYield) {
					secondBestI = bestI;
					secondBestYield = highestYield;
					highestYield = tempYield;
					bestI = i;		
				}
			}
			return CDOfferings[secondBestI];
		} else {
			return null;
		}
	}
	
	public static void clearCDOfferings() {
		MeritBank.CDOfferings = null;
	}
	
	public static void setCDOfferings(CDOffering[] offerings) {
		CDOfferings = offerings; 
	}
	
	public static double totalBalances() {
		double total = 0.0;		
		for (int i=0; i < MeritBank.numbOfAccountHolder; i++) {
			total += MeritBank.accountHolders[i].getCheckingBalance() + MeritBank.accountHolders[i].getCheckingBalance();
		}
		return total;
	}
	
	public static double recursionFutureValue(double amount, int years, double interestRate) {
		if (years == 0) {
			return amount;
		} else {
			return amount * (1 + interestRate) * recursionFutureValue(1, years - 1, interestRate);
		}
	}

	public static double futureValue(double presentValue, double interestRate, int term) {
		double futureVal = presentValue * Math.pow(1 + interestRate, term);
		return futureVal;
	}

	public static boolean processTransaction(Transaction transaction) throws NegativeAmountException, ExceedsFraudSuspicionLimitException, 
	ExceedsAvailableBalanceException {
		double amount = transaction.getAmount();
		BankAccount source = transaction.getSourceAccount();
		BankAccount target = transaction.getTargetAccount();
		if (Math.abs(transaction.getAmount()) > 1000) {
			MeritBank.fraudQueue.addTransaction(transaction);
			throw new ExceedsFraudSuspicionLimitException();
		}
		if (transaction.getAmount() < 0) {
			throw new NegativeAmountException();
		}
		if (transaction instanceof DepositTransaction) {
			target.deposit(amount);
			target.addTransaction(transaction);
		} else if (transaction instanceof WithdrawTransaction) {
			if (transaction.getAmount() + transaction.getTargetAccount().getBalance() < 0) {
				throw new ExceedsAvailableBalanceException();
			}
			target.withdraw(amount);
			transaction.getTargetAccount().addTransaction(transaction);
		} else if (transaction instanceof TransferTransaction) {
			if (source.getBalance() - amount  < 0) {
				throw new ExceedsAvailableBalanceException();
			}
			source.withdraw(amount);
			target.deposit(amount);
			transaction.getSourceAccount().addTransaction(transaction);
			transaction.getTargetAccount().addTransaction(transaction);
		}
		return true;
	}	
}
