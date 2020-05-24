package com.meritamerica.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.main.models.CDAccount;

public interface CDAccountRepo extends JpaRepository<CDAccount, Long> {

}
