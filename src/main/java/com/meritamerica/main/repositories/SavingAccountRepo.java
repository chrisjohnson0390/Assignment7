package com.meritamerica.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.meritamerica.main.models.SavingsAccount;

public interface SavingAccountRepo extends JpaRepository<SavingsAccount, Long> {

}