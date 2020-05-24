package com.meritamerica.main.services;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meritamerica.main.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.main.models.AccountHolder;
import com.meritamerica.main.models.CDAccount;
import com.meritamerica.main.models.CheckingAccount;
import com.meritamerica.main.models.SavingsAccount;
import com.meritamerica.main.repositories.CDAccountRepo;
import com.meritamerica.main.repositories.CDOfferRepo;
import com.meritamerica.main.repositories.CheckingAccountRepo;
import com.meritamerica.main.repositories.MyUserRepo;
import com.meritamerica.main.repositories.SavingAccountRepo;
import com.meritamerica.main.security.Users;

@Service
public class MyAccountService {
	@Autowired
	MyUserRepo userRepo;
	
	@Autowired
	CheckingAccountRepo checkingRepo;
	
	@Autowired
	SavingAccountRepo savingRepo;
	
	@Autowired
	CDAccountRepo cdaccRepo;
	
	@Autowired
	CDOfferRepo cdofferingRepo;
	
	public Users getUser(String username) {
		return userRepo.findByUserName(username);
	}
	
	public AccountHolder getMyAccountHolder(Principal principal) {
		Users user = userRepo.findByUserName(principal.getName());
		AccountHolder acc = user.getAccountHolder();
		return acc;
	}
	
	public CheckingAccount addChecking(CheckingAccount checking,Principal principal) throws ExceedsCombinedBalanceLimitException {
		AccountHolder acc = getMyAccountHolder(principal);
		checking.setAccHolder(acc);
		checking = checkingRepo.save(checking);
		return checking;
	}
	
	public List<CheckingAccount> getCheckings(Principal principal) {
		AccountHolder acc = getMyAccountHolder(principal);
		return acc.getCheckingAccounts();
	}
	
	public SavingsAccount addSaving(SavingsAccount saving,Principal principal) throws ExceedsCombinedBalanceLimitException {
		AccountHolder acc = getMyAccountHolder(principal);
		saving.setAccHolder(acc);
		saving = savingRepo.save(saving);
		return saving;
	}
	
	public List<SavingsAccount> getSavings(Principal principal) {
		AccountHolder acc = getMyAccountHolder(principal);
		return acc.getSavingsAccounts();
	}
	
	public CDAccount addCDAccount(CDAccount cda,Principal principal) throws ExceedsCombinedBalanceLimitException {
		AccountHolder acc = getMyAccountHolder(principal);
		cda.setAccHolder(acc);
		cda = cdaccRepo.save(cda);
		return cda;
	}
	
	public List<CDAccount> getCDAccount(Principal principal) throws ExceedsCombinedBalanceLimitException {
		AccountHolder acc = getMyAccountHolder(principal);
		return acc.getCDAccounts();
	}	
}