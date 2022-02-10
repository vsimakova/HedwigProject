package com.hedwigbookclub.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hedwigbookclub.domain.Book;
import com.hedwigbookclub.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/shopping_books")
	public String getBooks (ModelMap model) {
		List<Book> books = bookService.findAll();
		
		model.put("books", books);
		
		return "shopping_books";
	}
	
	@GetMapping("/{tId}" + "b")
	public String getBook (ModelMap model, @PathVariable Integer tId) {
		Book book = bookService.findById(tId);
		model.put("book", book);
		return "book_info";
	}
	
	@PostMapping("/{id}" + "b" +"/delete")
	public String deleteOneBook (@PathVariable Integer id) {
		bookService.delete(id);
		return "redirect:/shopping_books";
	}
}
