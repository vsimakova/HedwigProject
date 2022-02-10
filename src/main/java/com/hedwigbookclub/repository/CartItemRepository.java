package com.hedwigbookclub.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hedwigbookclub.domain.CartItem;
import com.hedwigbookclub.domain.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
 
	public List<CartItem> findByCustomer(User customer);
}
