package com.meritamerica.main.controllers;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.main.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.main.models.AccountHolder;
import com.meritamerica.main.models.CDAccount;
import com.meritamerica.main.models.CheckingAccount;
import com.meritamerica.main.models.SavingsAccount;
import com.meritamerica.main.services.MyAccountService;

@RequestMapping(value="/Me")
@RestController
public class MyAccountController {
	@Autowired
	MyAccountService accService;
	
	@GetMapping
	public AccountHolder getMyAccountHolder(Principal principal) {
		return accService.getMyAccountHolder(principal);
	}
	
	@PostMapping("/CheckingAccounts")
	public CheckingAccount addChecking(@RequestBody @Valid CheckingAccount checking,Principal principal) throws ExceedsCombinedBalanceLimitException {
		return accService.addChecking(checking, principal);
	}
	
	@GetMapping("/CheckingAccounts")
	public List<CheckingAccount> getCheckings(Principal principal) {
		return accService.getCheckings(principal);
	}
	
	@PostMapping("/SavingsAccounts")
	public SavingsAccount addSaving(@RequestBody @Valid SavingsAccount saving,Principal principal) throws ExceedsCombinedBalanceLimitException {
		return accService.addSaving(saving, principal);
	}
	
	@GetMapping("/SavingsAccounts")
	public List<SavingsAccount> getSavings(Principal principal) {
		return accService.getSavings(principal);
	}
	
	@PostMapping("/CDAccounts")
	public CDAccount addCDAccount(@RequestBody @Valid CDAccount cda,Principal principal) throws ExceedsCombinedBalanceLimitException {
		return accService.addCDAccount(cda, principal);
	}
	
	@GetMapping("/CDAccounts")
	public List<CDAccount> getCDAccount(Principal principal) throws ExceedsCombinedBalanceLimitException {
		return accService.getCDAccount(principal);
	}
}
