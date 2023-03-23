package com.example.review.service;

import com.example.review.entity.User;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
        		.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return new UserDetailsImpl(user);
    }

}
