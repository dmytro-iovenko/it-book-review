package com.example.review.service;

import com.example.review.model.User;
import com.example.review.model.UserDetailsImpl;
import com.example.review.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// load User by user name and returns a UserDetails object 
// that Spring Security can use for authentication and validation

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName)
        		.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return new UserDetailsImpl(user);
    }

}
