package com.hedwigbookclub.web;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hedwigbookclub.domain.Authorities;
import com.hedwigbookclub.domain.User;
import com.hedwigbookclub.service.AuthorityService;
import com.hedwigbookclub.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;
	
	@Autowired
	AuthorityService authorityService;
	
	@GetMapping("/register")
	public String getRegister(ModelMap model) {
		User user = new User();
		Authorities auth = new Authorities();
		model.addAttribute("user", user);
		model.addAttribute("auth", auth);
		return "register";
	}
	
	@PostMapping("/registration")
	public String postRegister(User user, Authorities auth) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodePassword = encoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		auth.setAuthority("ROLE_USER");
		auth.setUser(user);
		user.setAuthorities(new HashSet<>());
		user.getAuthorities().add(auth);
		userService.save(user);
		authorityService.save(auth);
		return "success";
	}
}
