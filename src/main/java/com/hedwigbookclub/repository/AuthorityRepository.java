package com.hedwigbookclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hedwigbookclub.domain.Authorities;
import com.hedwigbookclub.domain.User;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities, Integer> {
	
	public Authorities findByUser(User user);
}
