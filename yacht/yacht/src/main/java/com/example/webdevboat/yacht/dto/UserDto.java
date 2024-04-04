package com.example.webdevboat.yacht.dto;

import com.example.webdevboat.yacht.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {

  private Long id;
    private String name;
    private String email;
	private UserRole userRole;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
}