package com.hedwigbookclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hedwigbookclub.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
