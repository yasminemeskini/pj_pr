package com.example.webdevboat.yacht.services;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.webdevboat.yacht.model.User;
import com.example.webdevboat.yacht.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@Service 
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	 @Autowired
	    private UserRepository userRepository;
	

	@Override
	public UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub
		return new UserDetailsService() {
			
			
			
			public UserDetails loadUserByUsername(String username) {
				return userRepository.findFirstByEmail(username).orElseThrow(()->new UsernameNotFoundException("usernot found"));
				
			}
		};
	}
	@Override
	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }
	
}
