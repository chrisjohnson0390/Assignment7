package com.meritamerica.main.dev;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import com.meritamerica.main.repositories.MyUserRepo;
import com.meritamerica.main.security.Users;

@Service
public class DBinit implements CommandLineRunner {
	@Autowired
	MyUserRepo userRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Users chris = new Users("chris", "123", "USER_PRIVILEGE");
		Users fikerte = new Users("fikerte", "123", "USER_PRIVILEGE");
		Users admin = new Users("admin", "123", "ADMIN_PRIVILEGE");		
		List<Users> users = Arrays.asList(fikerte, admin, chris);
		this.userRepo.saveAll(users);
	}
}