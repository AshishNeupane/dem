package com.ann.dem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class demoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(demoApplication.class, args);
    }

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		
		return builder.sources(demoApplication.class);
	}
	
	 @Value("${server.instance.id}")
	    String instanceId;

	    @GetMapping("/hello")
	    public String hello() {
	        return String.format("Hello from instance %s", instanceId);
	    }
    
}
