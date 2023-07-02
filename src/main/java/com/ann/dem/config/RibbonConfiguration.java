package com.ann.dem.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class RibbonConfiguration {

	@Bean
	  @LoadBalanced
	  public RestTemplate restTemplate() 
	  {
	       return new RestTemplate();
	  }
}
