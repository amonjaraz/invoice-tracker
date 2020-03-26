package com.alexmonjaraz.customuserregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alexmonjaraz.customuserregistration.DAO.UserRepo;
import com.alexmonjaraz.customuserregistration.entity.MyUserDetail;
import com.alexmonjaraz.customuserregistration.entity.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(username);
		if (user == null) throw new UsernameNotFoundException("User Not Found");
		
		UserDetails userDetails = new MyUserDetail(user);
		return userDetails;
	}

}
