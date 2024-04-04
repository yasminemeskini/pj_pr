package com.example.webdevboat.yacht.services;

import com.example.webdevboat.yacht.dto.SignupRequest;
import com.example.webdevboat.yacht.dto.UserDto;

public interface AuthService {
UserDto createCustomer(SignupRequest singupRequest);

boolean hasCustomerWithEmail(String email);

}
