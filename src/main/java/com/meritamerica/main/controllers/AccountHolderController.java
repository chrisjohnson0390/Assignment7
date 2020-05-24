package com.meritamerica.main.controllers;

import java.util.List;
import javax.validation.Valid;
import com.meritamerica.main.exceptions.*;
import com.meritamerica.main.models.*;
import com.meritamerica.main.services.AccountHolderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/AccountHolders")
@RestController
public class AccountHolderController {
	Logger logger = LoggerFactory.getLogger(AccountHolderController.class);

	@Autowired
	AccountHolderService accHolderService;
	
	@PostMapping(value = "/")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder createAccountHolder(@RequestBody @Valid AccountHolder newAccountHolder) {	
		return this.accHolderService.createAccountHolder(newAccountHolder);
	}
	
	@GetMapping(value="/")
	public List<AccountHolder> getAccountHolders() {
		return this.accHolderService.getAccountHolders();
	}
	
	@GetMapping(value="/{id}") 
	public AccountHolder getAccountHolder(@PathVariable("id") long id) throws NotFoundException {
		return this.accHolderService.getAccountHolder(id);
	}
	
	@PostMapping(value="/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckingAccount addChecking(@PathVariable("id") long id, @RequestBody @Valid CheckingAccount checking ) throws NotFoundException, ExceedsCombinedBalanceLimitException, NegativeAmountException {				
		return this.accHolderService.addChecking(id, checking);
	}
	
	@GetMapping(value="/{id}/CheckingAccounts")
	public List<CheckingAccount> getChecking(@PathVariable("id") long id) throws NotFoundException {
		return this.accHolderService.getChecking(id);
	}
	
	@PostMapping(value="/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount addSaving(@PathVariable("id") long id, @RequestBody @Valid SavingsAccount savings ) throws NotFoundException, ExceedsCombinedBalanceLimitException, NegativeAmountException {	
		return this.accHolderService.addSaving(id, savings);
	}
	
	@GetMapping(value="/{id}/SavingsAccounts")
	public List<SavingsAccount> getSavings(@PathVariable("id") long id) throws NotFoundException {
		return this.accHolderService.getSavings(id);
	}

	@PostMapping(value="/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CDAccount addCDAccount(@PathVariable("id") long id, @RequestBody @Valid CDAccount CDAccount ) throws NotFoundException, ExceedsCombinedBalanceLimitException, NegativeAmountException, ExceedsFraudSuspicionLimitException, FieldErrorException {			
		return this.accHolderService.addCDAccount(id, CDAccount);
	}
	
	@GetMapping(value="/{id}/CDAccounts")
	public List<CDAccount> getCDAccounts(@PathVariable("id") long id) throws NotFoundException {
		return this.accHolderService.getCDAccounts(id);
	}
}
