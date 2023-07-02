package com.ann.dem.service;

import com.ann.dem.dto.UserInformationDTO;
import com.ann.dem.model.*;
import com.ann.dem.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

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
}
