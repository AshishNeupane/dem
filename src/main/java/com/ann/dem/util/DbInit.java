package com.ann.dem.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ann.dem.model.Role;
import com.ann.dem.model.User;
import com.ann.dem.repository.RoleRepository;
import com.ann.dem.repository.UserRepository;



@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepo;
    private PasswordEncoder passwordEncoder;
    
    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if(userRepository.findAll().isEmpty()) {
        	
        	Role adminRole = new Role();
        	adminRole.setName("ADMIN");
        	Role managerRole = new Role();
        	managerRole.setName("MANAGER");
        	Role userRole = new Role();
        	userRole.setName("USER");
        	
        	List<Role> roles = Arrays.asList(managerRole, userRole);
        	if(roleRepo.findAll().isEmpty()) {
        		roleRepo.saveAll(roles);
        	}
        	
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
