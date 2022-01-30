package com.hedwigbookclub.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hedwigbookclub.domain.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	public Optional<Book> findById(Integer id);
}
