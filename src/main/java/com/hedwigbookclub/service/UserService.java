package com.hedwigbookclub.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hedwigbookclub.domain.User;
import com.hedwigbookclub.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public List<User> findAll () {
		return userRepo.findAll();
	}
	
	public User findById(Integer userId) {
		User user = userRepo.getById(userId);
		return user;
	}
	
	public User save(User user) {
		return userRepo.save(user);
	}
	
	public void delete(Integer id) {
		userRepo.deleteById(id);
	}
}
