package com.hedwigbookclub.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hedwigbookclub.domain.User;
import com.hedwigbookclub.service.AdminService;
import com.hedwigbookclub.service.UserService;

@Controller
public class DashboardController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/dashboard")
	public String getDashboard (@AuthenticationPrincipal User user, ModelMap model) {
		
		model.put("user", user);
		return "dashboard";
	}
	
	@GetMapping("/users")
	public String listUsers (@AuthenticationPrincipal User user, ModelMap model) {
		
		if(user.getAuthorities().equals("ROLE_USER")) {
			List<User> users = adminService.getAllUserAccounts();
			model.put("users", users);
		}
		List<User> users = userService.findAll();
		model.put("users", users);
		return "users";
	}
	
	@GetMapping("/{userId}")
	public String getOneUser (ModelMap model, @PathVariable Integer userId) {
		User user = userService.findById(userId);
		model.put("users", Arrays.asList(user));
		model.put("user", user);
		return "user";
	}
	
	@PostMapping("/users/{id}/delete")
	public String deleteOneUser (@PathVariable Integer id) {
		userService.delete(id);
		return "redirect:/users";
	}
	
	@GetMapping("/contact")
	public String goContact () {
		return "contact";
	}
	
	@GetMapping("/aboutus")
	public String goAboutus () {
		return "aboutus";
	}
	
	@GetMapping("/book")
	public String goToBook () {
		return "book";
	}
	
	@GetMapping("/bookclub")
	public String goToBookClub () {
		return "bookclub";
	}

	@GetMapping("/gallery")
	public String goToGallery () {
		return "gallery";
	}
	
	@GetMapping("/index")
	public String goToHome () {
		return "index";
	}
	

	
	@PostMapping("/dashboard")
	public String dashboard () {
		return "redirect:/dashboard";
	}
}
