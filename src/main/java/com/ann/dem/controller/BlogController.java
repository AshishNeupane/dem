package com.ann.dem.controller;

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
import com.ann.dem.model.Blog;
import com.ann.dem.repository.BlogRepository;
import com.ann.dem.service.BlogService;

@RestController
@RequestMapping("/blog/")
public class BlogController {
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private MessageSource messageSource;

	 @GetMapping("/view")
	    public Page<Blog> getAllZone(Pageable pageable)
	    {
	        return blogRepository.findAll(pageable);
	    }
	
	 @PostMapping("/post")
	    public RestMessage createBlog(/*@Valid*/@RequestBody Blog blog, Locale locale) throws RestMessageException
	    {
	        if(blogService.createBlog(blog) != null)
	        {
	            String successMessage =  messageSource.getMessage("Blog.add.success", null, locale);
	            return new RestSuccessMessage(successMessage);
	        }
	        else
	        {
	            return new RestErrorMessage("Internal Server Error");
	        }
	    }
	 
	 @PutMapping("/update/{blogId}")
	    public RestMessage updateBlog(@PathVariable Long blogId, @Valid @RequestBody Blog blogRequest, Locale locale) throws RestMessageException
	    {
	        if(blogService.updateBlog(blogId,blogRequest)!= null)
	        {
	            String successMessage =  messageSource.getMessage("Blog.update.success", null, locale);
	            	return new RestSuccessMessage(successMessage);
	        	}
	        
	        else
	        {
	            return new RestErrorMessage("Internal Server Error");
	        }
	    }
	 
	 @DeleteMapping("/delete/{blogId}")
	    public ResponseEntity<?> deleteBlog(@PathVariable Long blogId, Locale locale) throws RestMessageException
	    {    	
	    	if(blogService.deleteBlog(blogId)!=null) {    	
	           	String successMessage = messageSource.getMessage("Blog.delete.success", null, locale);           	
	           	return new ResponseEntity<>(new RestSuccessMessage(successMessage),
	                        HttpStatus.ACCEPTED);           	 
	        }
	    	else {    		
	            return new ResponseEntity<>(new RestErrorMessage("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);            
	       }
	    	 
	    }
	 
}
