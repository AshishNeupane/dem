package com.ann.dem.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ann.dem.model.User;
import com.ann.dem.repository.UserRepository;

@Service
public class UserActivityService {
//    @Autowired
//    private UserRepository userRespository;
//
//    
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    
//    @Transactional
//    public User changePassword(User user, String newPassword) 
//    {
//        if(user.getPassword() != null) {
//            user.setPassword(passwordEncoder.encode(newPassword));
//            userRespository.save(user);
//        }
//        else {
//            user = null;
//        }
//        
//        return user;
//    }
}
