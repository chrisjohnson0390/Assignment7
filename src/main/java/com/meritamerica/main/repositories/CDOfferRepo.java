package com.meritamerica.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.meritamerica.main.models.CDOffering;

public interface CDOfferRepo extends JpaRepository<CDOffering, Long> {
	
}
