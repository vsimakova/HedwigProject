package com.hedwigbookclub.service;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hedwigbookclub.domain.Book;
import com.hedwigbookclub.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepo;
	
	public List<Book> findAll () {
		List<Book> books = bookRepo.findAll();
		Collections.sort(books, (book1, book2) -> 
		book1.getTitle().compareTo(book2.getTitle())); 
		return books;
	}

	public Book save(Book book) {
		return bookRepo.save(book);
	}

	public void delete(Integer id) {
		bookRepo.deleteById(id);
	}

	public Book findById(Integer tId) {
		return bookRepo.getById(tId);
	}
}

	