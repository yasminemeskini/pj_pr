package com.example.webdevboat.yacht.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevboat.yacht.dto.AuthenticationRequest;
import com.example.webdevboat.yacht.dto.AuthenticationResponse;
import com.example.webdevboat.yacht.dto.SignupRequest;
import com.example.webdevboat.yacht.dto.UserDto;

import com.example.webdevboat.yacht.model.User;
import com.example.webdevboat.yacht.repository.UserRepository;
import com.example.webdevboat.yacht.services.AuthService;
import com.example.webdevboat.yacht.services.UserService;
import com.example.webdevboat.yacht.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth") 
@RequiredArgsConstructor
public class AuthController {

	@Autowired
	private AuthService authService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@PostMapping("/signup")
	    public ResponseEntity<?> signupCustomer (@RequestBody SignupRequest signupRequest) {
	        if (authService.hasCustomerWithEmail(signupRequest.getEmail())) {
	            return new ResponseEntity<>("Customer already exists with this email", HttpStatus.CONFLICT);
	        }
	        UserDto createdCustomerDto = authService.createCustomer(signupRequest);
	        if (createdCustomerDto == null) {
	            return new ResponseEntity<>("Customer not created, try again later", HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
	    }

	  @PostMapping("/login")
public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws
	  BadCredentialsException,
	  DisabledException,
	  UsernameNotFoundException{
	  
	 try{
		 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
				 (authenticationRequest.getEmail(), authenticationRequest.getPassword())) ;
		 
		 
	 }catch(BadCredentialsException e){
		throw new BadCredentialsException("Incorrect username or password"); 
		 
	 }
	  final UserDetails userDetails=userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
	  Optional<User>optionalUser=userRepository.findFirstByEmail(userDetails.getUsername());
	  final String jwt =jwtUtil.generateToken(userDetails);
	  
	  AuthenticationResponse authenticationResponse =new AuthenticationResponse();
	  if(optionalUser.isPresent()) {
		  authenticationResponse.setJwt(jwt);
		  authenticationResponse.setUserId(optionalUser.get().getId());
		  authenticationResponse.setUserRole(optionalUser.get().getUserRole());
	  
	  
	  
	  }
		  
	  return authenticationResponse;
	  
	  
	  }
	  
	  
	  }
	 /*
	    @PostMapping("/login")
	    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
	        Optional<User> optionalUser = userRepository.findByEmail(authenticationRequest.getEmail());
	        if (optionalUser.isPresent() && new BCryptPasswordEncoder().matches(authenticationRequest.getPassword(), optionalUser.get().getPassword())) {
	            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
	            authenticationResponse.setUserId(optionalUser.get().getId());
	            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
	            return ResponseEntity.ok(authenticationResponse);
	        }
	        return ResponseEntity.badRequest().body("Incorrect email or password");
	    }*/
	