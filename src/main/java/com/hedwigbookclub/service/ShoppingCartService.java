package com.hedwigbookclub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hedwigbookclub.domain.Book;
import com.hedwigbookclub.domain.CartItem;
import com.hedwigbookclub.domain.User;
import com.hedwigbookclub.repository.BookRepository;
import com.hedwigbookclub.repository.CartItemRepository;

@Service
public class ShoppingCartService {
	
	@Autowired
	private CartItemRepository cartRepo;
	
	@Autowired
	private BookRepository bookRepo;
	
	public List<CartItem> listCartItems(User customer) {
		return cartRepo.findByCustomer(customer);
	}

	public void addToCart(Integer id, User user) {
		Book book = bookRepo.getById(id);
		CartItem cartItem = new CartItem();
		cartItem.setCustomer(user);
		cartItem.setBook(book);
		cartRepo.save(cartItem);
	}
	
	public void deleteBook(Integer id) {
		Book book = bookRepo.getById(id);
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartRepo.deleteById(id);
	}

	public void emptyCart(User user) {
		List<CartItem>list = listCartItems(user);
		for (int i = 0; i < list.size(); i++) cartRepo.deleteById(list.get(i).getId());
	}

}
