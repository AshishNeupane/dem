package com.ann.dem.service;

import com.ann.dem.dto.UserInformationDTO;
import com.ann.dem.exception.RestMessageException;
import com.ann.dem.model.*;
import com.ann.dem.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;

    public User findByUsername(String username)
    {
       return userRepository.findByUsername(username);
    }
    
    public UserInformationDTO getUserInformationByUsername(String username)
    {
        //TODO change to single query
        User user = userRepository.findByUsername(username);
        
        UserInformationDTO userInformationDTO = new UserInformationDTO();
        userInformationDTO.setUsername(user.getUsername());
  
        return userInformationDTO;
    }
    
    public User createUser(User user) throws RestMessageException
    {
    	 user.setPassword(passwordEncoder.encode(user.getPassword()));
    	    Date date = new Date();  
    	 if(user.getDateOfBirth().compareTo(date)>0 ) {
    		 throw new RestMessageException("Date exceeds current date");
    	 }
    	return userRepository.save(user);
    }
    
    public User updateUser(Long userId,User userRequest) throws RestMessageException
    {
    	return userRepository.findById(userId).map(user ->{
    		 Date date = new Date(); 
    		if(user.getDateOfBirth().compareTo(date)>0 ) {
       		 throw new RuntimeException("Date exceeds current date");
       	 }
    		user.setUsername(userRequest.getUsername());
    		user.setFirstName(userRequest.getFirstName());
    		user.setLastName(userRequest.getLastName());
    		user.setEmailId(userRequest.getEmailId());
    		user.setDateOfBirth(userRequest.getDateOfBirth());
   	 		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
   	 		return userRepository.save(user);
    	}).orElseThrow(() -> new RuntimeException());
    	}
    
    public ResponseEntity<?> deleteUser(Long UserId) throws RestMessageException
    {	
            userRepository.deleteById(UserId);
            return ResponseEntity.ok().build();
    }
    
    
    public User getUserName(String username) throws RestMessageException
    {
    	return userRepository.findByUsername(username);
    }
    
    public List<User> getUserFirstName(String firstname, Pageable pageable) throws RestMessageException
    {
    	return userRepository.findByFirstName(firstname, pageable);
    }
    
    public List<User> getUserLastName(String lastname) throws RestMessageException
    {
    	return userRepository.findByLastName(lastname);
    }
    
    public User getUserEmailId(String emailId) throws RestMessageException
    {
    	return userRepository.findByEmailId(emailId);
    }
}
