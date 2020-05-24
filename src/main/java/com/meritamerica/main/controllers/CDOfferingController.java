package com.meritamerica.main.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.main.exceptions.FieldErrorException;
import com.meritamerica.main.exceptions.NotFoundException;
import com.meritamerica.main.models.CDOffering;
import com.meritamerica.main.services.CDOfferingService;

@RestController
@RequestMapping(value="/CDOfferings")
public class CDOfferingController {
	@Autowired
	CDOfferingService CDOService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering createCDOffering(@RequestBody @Valid CDOffering offering) throws FieldErrorException {				
		return this.CDOService.createCDOffering(offering);
	}
	
	@GetMapping
	public List<CDOffering> getCDOfferings() throws NotFoundException {
	    return this.CDOService.getCDOfferings();
	}
}
