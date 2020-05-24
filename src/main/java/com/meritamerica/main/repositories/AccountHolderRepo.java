package com.meritamerica.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.main.models.AccountHolder;

public interface AccountHolderRepo extends JpaRepository<AccountHolder, Long> {

}
