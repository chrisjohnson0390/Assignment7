package com.meritamerica.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.meritamerica.main.models.CheckingAccount;

public interface CheckingAccountRepo extends JpaRepository<CheckingAccount, Long> {
	
}
