package com.hedwigbookclub.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hedwigbookclub.domain.Book;
import com.hedwigbookclub.domain.CartItem;
import com.hedwigbookclub.domain.User;
import com.hedwigbookclub.service.BookService;
import com.hedwigbookclub.service.ShoppingCartService;

@Controller
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/cart")
	public String showShoppingCart(Model model, @AuthenticationPrincipal User user) {
		List<CartItem> cartItems = shoppingCartService.listCartItems(user);
		float total = 0;
		for(int i = 0; i <cartItems.size(); i++) {
			total+=cartItems.get(i).getBook().getPrice();
		}
		
		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("total", total);
		model.addAttribute("pageTitle", "Shopping Cart");
		return "cart";
	}
	
	@PostMapping("/{id}/toCart")
	public String addBookToCart (@PathVariable Integer id, ModelMap model, @AuthenticationPrincipal User user) {
		if(user == null) {
			return "redirect:/login";
		}
		shoppingCartService.addToCart(id, user);
		return "redirect:/shopping_books";
	}
	
	@PostMapping("/thx")
	public String goToThx (@AuthenticationPrincipal User user) {
		shoppingCartService.emptyCart(user);
		return "thx";
	}
}
