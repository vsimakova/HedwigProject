package com.hedwigbookclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hedwigbookclub.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u" 
		+ " left join fetch u.authorities" 
		+ " where u.username = :username")
	User findByUsername(@Param(value="username")String username);
}
