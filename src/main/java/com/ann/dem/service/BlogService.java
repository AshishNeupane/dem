package com.ann.dem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ann.dem.exception.ResourceNotFoundException;
import com.ann.dem.exception.RestMessageException;
import com.ann.dem.model.Blog;
import com.ann.dem.repository.BlogRepository;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	 
	    public Blog createBlog(Blog blog) throws RestMessageException
	    {
	    	return blogRepository.save(blog);
	    }
	    
	    public Blog updateBlog(Long blogId, Blog blogRequest) throws RestMessageException
	    {
	    	try {
		        return blogRepository.findById(blogId).map(blog -> {
		        	blog.setTitle(blogRequest.getTitle());
		        	blog.setBody(blogRequest.getBody());
		            return blogRepository.save(blog);
		        }).orElseThrow(() -> new ResourceNotFoundException(""));
	    	}catch(Exception e) {
	    		throw new RestMessageException("Internal Sever Error");
	    	}
	    }
	    
	    public ResponseEntity<?> deleteBlog(Long blogId) throws RestMessageException
	    {	
	            blogRepository.deleteById(blogId);
	            return ResponseEntity.ok().build();
	    }
}
