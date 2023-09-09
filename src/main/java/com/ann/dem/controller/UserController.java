package com.ann.dem.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ann.dem.exception.RestMessageException;
import com.ann.dem.message.RestMessage;
import com.ann.dem.message.error.RestErrorMessage;
import com.ann.dem.message.success.RestSuccessMessage;

import com.ann.dem.model.User;

import com.ann.dem.service.UserService;

@RestController
@RequestMapping("/user/")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;

	 @GetMapping("/api/userName/{username}")
	    public User getUserByUsername(@PathVariable String username) throws RestMessageException
	    {
		 return userService.getUserName(username);
	    }
	 
	 @GetMapping("/api/usersFirstName/{firstname}")
	    public List<User> getUserfirstName(@PathVariable String firstname, Pageable pageable) throws RestMessageException
	    {
	          return userService.getUserFirstName(firstname, pageable);
	    }
	 
	 @GetMapping("/api/usersLastName/{lastname}")
	    public List<User> getUserlastName(@PathVariable String lastname) throws RestMessageException
	    {
		 return userService.getUserLastName(lastname);
	    }
	 
	 @GetMapping("/api/userEmailId/{emailId}") 
	    public User getUserEmailId(@PathVariable String emailId) throws RestMessageException
	    {
		 return userService.getUserEmailId(emailId);
	    }
	
	 @PostMapping("/api/post")
	    public RestMessage createUser(@RequestBody User user, Locale locale) throws RestMessageException
	    {
	        if(userService.createUser(user) != null)
	        {
	            String successMessage =  messageSource.getMessage("user.add.success", null, locale);
	            return new RestSuccessMessage(successMessage);
	        }
	        else
	        {
	            return new RestErrorMessage("Internal Server Error");
	        }
	    }
	 
	 @PutMapping("/api/user/{id}")
	    public RestMessage updateUser(@PathVariable Long id,@RequestBody User user, Locale locale) throws RestMessageException
	    {
	        if(userService.updateUser(id,user) != null)
	        {
	            String successMessage =  messageSource.getMessage("user.update.success", null, locale);
	            return new RestSuccessMessage(successMessage);
	        }
	        else
	        {
	            return new RestErrorMessage("Internal Server Error");
	        }
	    }
	 
	
	 
	 @DeleteMapping("/delete/{userId}")
	    public ResponseEntity<?> deleteBlog(@PathVariable Long userId, Locale locale) throws RestMessageException
	    {    	
	    	if(userService.deleteUser(userId)!=null) {    	
	           	String successMessage = messageSource.getMessage("user.delete.success", null, locale);           	
	           	return new ResponseEntity<>(new RestSuccessMessage(successMessage),
	                        HttpStatus.ACCEPTED);           	 
	        }
	    	else {    		
	            return new ResponseEntity<>(new RestErrorMessage("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);            
	       }
	    	 
	    }
	 
}
