package com.hedwigbookclub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hedwigbookclub.domain.Order;
import com.hedwigbookclub.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	public Order save(Order order) {
		return orderRepo.save(order);
	}
}
