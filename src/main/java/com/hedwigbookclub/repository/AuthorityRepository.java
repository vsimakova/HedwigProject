package com.hedwigbookclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hedwigbookclub.domain.Authorities;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities, Integer> {
	
}
