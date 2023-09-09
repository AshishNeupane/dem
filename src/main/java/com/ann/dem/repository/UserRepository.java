package com.ann.dem.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ann.dem.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	User findByUsernameAndDeleted(String username, Boolean Deleted);
	List<User> findByFirstName(String firstname, Pageable pageable);
	List<User> findByLastName(String lastname);
	User findByEmailId(String emailId);

}
