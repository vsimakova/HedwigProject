package com.hedwigbookclub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hedwigbookclub.domain.Authorities;
import com.hedwigbookclub.domain.User;
import com.hedwigbookclub.repository.AuthorityRepository;

@Service
public class AuthorityService {
	
	@Autowired
	private AuthorityRepository authRepo;
	
	public Authorities save(Authorities auth) {
		return authRepo.save(auth);
	}
	
	public void deleteAuthorities(User user) {
		Authorities auth = authRepo.findByUser(user);
		authRepo.deleteById(auth.getId());
	}

}
