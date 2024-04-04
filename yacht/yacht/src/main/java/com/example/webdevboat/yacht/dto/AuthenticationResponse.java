package com.example.webdevboat.yacht.dto;

import com.example.webdevboat.yacht.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {
private String jwt;
private UserRole userRole;
private Long userId;
/**
 * @return the jwt
 */
public String getJwt() {
	return jwt;
}
/**
 * @param jwt the jwt to set
 */
public void setJwt(String jwt) {
	this.jwt = jwt;
}
/**
 * @return the userRole
 */
public UserRole getUserRole() {
	return userRole;
}
/**
 * @param userRole the userRole to set
 */
public void setUserRole(UserRole userRole) {
	this.userRole = userRole;
}
/**
 * @return the userId
 */
public Long getUserId() {
	return userId;
}
/**
 * @param userId the userId to set
 */
public void setUserId(Long userId) {
	this.userId = userId;
}

}
