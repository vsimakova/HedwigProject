package com.hedwigbookclub.web;

import java.util.ArrayList;
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
import com.hedwigbookclub.domain.Order;
import com.hedwigbookclub.domain.User;
import com.hedwigbookclub.service.OrderService;
import com.hedwigbookclub.service.ShoppingCartService;

@Controller
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/cart")
	public String showShoppingCart(Model model, @AuthenticationPrincipal User user) {
		List<CartItem> cartItems = shoppingCartService.listCartItems(user);
		float total = 0;
		List<Book> books = new ArrayList<>();
		for(int i = 0; i <cartItems.size(); i++) {
			total+=cartItems.get(i).getBook().getPrice();
			books.add(cartItems.get(i).getBook());
		}
		Order order = new Order();
		model.addAttribute("books", books);
		model.addAttribute("order", order);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("total", total);
		model.addAttribute("pageTitle", "Shopping Cart");
		return "cart";
	}
	
	@PostMapping("/placing_order")
	public String placeOrder(@AuthenticationPrincipal User user, Order order) {
		shoppingCartService.emptyCart(user);
		orderService.save(order);
		return "thx";
	}
	
	@PostMapping("/{id}/toCart")
	public String addBookToCart (@PathVariable Integer id, ModelMap model, @AuthenticationPrincipal User user) {
		if(user == null) {
			return "redirect:/login";
		}
		shoppingCartService.addToCart(id, user);
		return "redirect:/shopping_books";
	}

}
