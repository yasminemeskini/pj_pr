package com.example.webdevboat.yacht.dto;

import java.util.Date;

import com.example.webdevboat.yacht.model.BookBoatStatus;

import lombok.Data;

@Data
public class BookABoatDto {
    private Long id;
    private String fromDate;
    private String toDate;
    private Long days;
    private int price;
private BookBoatStatus bookBoatStatus;
private Long boatId;
private Long userId;
private String username;
private String email;


/**
 * @return the username
 */
public String getUsername() {
	return username;
}
/**
 * @param username the username to set
 */
public void setUsername(String username) {
	this.username = username;
}
/**
 * @return the email
 */
public String getEmail() {
	return email;
}
/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}
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

/**
 * @return the price
 */
public int getPrice() {
	return price;
}
/**
 * @param price the price to set
 */
public void setPrice(int price) {
	this.price = price;
}
/**
 * @return the bookBoatStatus
 */
public BookBoatStatus getBookBoatStatus() {
	return bookBoatStatus;
}
/**
 * @param bookBoatStatus the bookBoatStatus to set
 */
public void setBookBoatStatus(BookBoatStatus bookBoatStatus) {
	this.bookBoatStatus = bookBoatStatus;
}
/**
 * @return the boatId
 */
public Long getBoatId() {
	return boatId;
}
/**
 * @param boatId the boatId to set
 */
public void setBoatId(Long boatId) {
	this.boatId = boatId;
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

/**
 * @return the fromDate
 */
public String getFromDate() {
	return fromDate;
}
/**
 * @param fromDate the fromDate to set
 */
public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}
/**
 * @return the toDate
 */
public String getToDate() {
	return toDate;
}
/**
 * @param toDate the toDate to set
 */
public void setToDate(String toDate) {
	this.toDate = toDate;
}
public Long getDays() {
	return days;
}
/**
 * @param days the days to set
 */
public void setDays(Long days) {
	this.days = days;
}






}