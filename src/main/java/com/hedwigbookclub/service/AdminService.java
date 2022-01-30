package com.hedwigbookclub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hedwigbookclub.domain.User;
import com.hedwigbookclub.repository.UserRepository;

@Service
public class AdminService {
	
	//something what admin can do and user not
	
	@Autowired
	private UserRepository userRepo;
	
	@Secured({"ROLE_ADMIN", "ROLE_SUPERUSER"})
	public List<User> getAllUserAccounts() {
		return userRepo.findAll();
	}
}
