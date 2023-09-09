package com.ann.dem.util;



import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ann.dem.model.User;
import com.ann.dem.repository.UserRepository;



@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    
    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if(userRepository.findAll().isEmpty()) {
        	
        	
        	
            // Create users
            User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");        
           
            try {
            	userRepository.save(admin);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}           
        }
    }
}
