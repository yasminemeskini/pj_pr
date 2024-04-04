package com.example.webdevboat.yacht.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.webdevboat.yacht.dto.SignupRequest;
import com.example.webdevboat.yacht.dto.UserDto;
import com.example.webdevboat.yacht.enums.UserRole;
import com.example.webdevboat.yacht.model.User;
import com.example.webdevboat.yacht.repository.UserRepository;
import com.example.webdevboat.yacht.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void createAdminAccount() {
		
		User adminAccount=userRepository.findByUserRole(UserRole.ADMIN);		
		if(adminAccount==null) {
		User newAdminAccount=new User();
		newAdminAccount.setName("Admin");
		newAdminAccount.setEmail("admin@gmail.com");
		newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
		newAdminAccount.setUserRole(UserRole.ADMIN);		
		userRepository.save(newAdminAccount);
		System.out.println("Admin account created successfuly");
		
		
		}
		
		
	}


	@Override
	public UserDto createCustomer(SignupRequest singupRequest) {
User user= new User();
user.setName(singupRequest.getName());
user.setEmail(singupRequest.getEmail());
user.setPassword(new BCryptPasswordEncoder().encode(singupRequest.getPassword()));
user.setUserRole(UserRole.CUSTOMER);
User createdUser =userRepository.save(user);
UserDto userDto =new UserDto();
userDto.setId(createdUser.getId());


return userDto;
	}
	
	@Override
	public boolean hasCustomerWithEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findFirstByEmail(email).isPresent();
		
	}
}
