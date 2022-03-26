package com.hedwigbookclub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hedwigbookclub.domain.User;
import com.hedwigbookclub.repository.UserRepository;
import com.hedwigbookclub.security.CustomSecurityUser;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		return new UserDetailServiceImpl();
//	}
	
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuth
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Username and or password was incorrect.");
		}
		return new CustomSecurityUser(user);
	}

}
