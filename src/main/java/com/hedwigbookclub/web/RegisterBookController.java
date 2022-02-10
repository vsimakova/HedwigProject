package com.hedwigbookclub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.hedwigbookclub.domain.Book;
import com.hedwigbookclub.domain.User;
import com.hedwigbookclub.service.BookService;

@Controller
public class RegisterBookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/register_book")
	public String getRegisterBook(@AuthenticationPrincipal User user, ModelMap model) {
		Book book = new Book();
		model.addAttribute("book", book);
//		model.put("user", user);
		return "register_book";
	}
	
	@PostMapping("/registration_book")
	public String postRegisterBook(Book book) {
		bookService.save(book);
		return "success_register_book";
	}
}
