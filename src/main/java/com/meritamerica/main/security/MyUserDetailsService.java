package com.meritamerica.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.meritamerica.main.repositories.MyUserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	MyUserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users aUser = userRepo.findByUserName(username);
        MyUserDetail userDetail = new MyUserDetail(aUser);
        System.out.println("loadUserByUsername in detail service " + userDetail.getUsername() + " password " + userDetail.getPassword());
        return userDetail;
	}	
}
