package com.meritamerica.main.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meritamerica.main.exceptions.FieldErrorException;
import com.meritamerica.main.exceptions.NotFoundException;
import com.meritamerica.main.models.CDOffering;
import com.meritamerica.main.models.MeritBank;
import com.meritamerica.main.repositories.CDOfferRepo;

@Service
public class CDOfferingService {
	@Autowired
	CDOfferRepo cdofferingRepo;
	
	public CDOffering createCDOffering(CDOffering offering) throws FieldErrorException {		
		MeritBank.addCDOffering(offering); 
		offering = cdofferingRepo.save(offering);
		return offering;
	}
	
	public List<CDOffering> getCDOfferings() throws NotFoundException {
	    return cdofferingRepo.findAll();
	}
}