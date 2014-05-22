package org.mmss.contacs.service;

import org.mmss.contacs.dto.UserDto;
import org.mmss.contacts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
 
    public UserDto findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
