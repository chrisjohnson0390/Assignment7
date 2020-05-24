package com.meritamerica.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.meritamerica.main.security.Users;

public interface MyUserRepo extends JpaRepository<Users, Long> {
	@Query("Select u from Users u where u.username = ?1")
	Users findByUserName(String username);
}