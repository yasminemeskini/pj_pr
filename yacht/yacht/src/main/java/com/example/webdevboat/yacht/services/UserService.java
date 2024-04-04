package com.example.webdevboat.yacht.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.webdevboat.yacht.model.User;
import java.util.List;


public interface UserService {
UserDetailsService userDetailsService();

List<User> getAllUsers();


}
