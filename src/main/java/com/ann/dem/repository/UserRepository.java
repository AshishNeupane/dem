package com.ann.dem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ann.dem.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	User findByUsernameAndDeleted(String username, Boolean Deleted);

}
