package com.example.webdevboat.yacht.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.webdevboat.yacht.dto.BookABoatDto;

import lombok.Data;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Entity
@Data
public class BookABoat {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;


private String fromDate;
private String toDate;
private Long days;

	    private int price;
	    @Enumerated(EnumType.STRING)
	    private BookBoatStatus bookBoatStatus;
	    
 @ManyToOne(fetch = FetchType.LAZY,optional = false)
 @JoinColumn(name = "user_id",nullable = false)
 @OnDelete(action = OnDeleteAction.CASCADE)
 @JsonIgnore
 private User user;
 
 @ManyToOne(fetch = FetchType.LAZY,optional = false)
 @JoinColumn(name = "boat_id",nullable = false)
 @OnDelete(action = OnDeleteAction.CASCADE)
 @JsonIgnore
 private Boat boat;
 
 
/**
 * @return the nbp
 */






/**
 * @return the days
 */
public Long getDays() {
	return days;
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



/**
 * @param days the days to set
 */
public void setDays(Long days) {
	this.days = days;
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
 * @return the user
 */
public User getUser() {
	return user;
}



/**
 * @param user the user to set
 */
public void setUser(User user) {
	this.user = user;
}



public Boat getBoat() {
	return boat;
}



public void setBoat(Boat boat) {
	this.boat = boat;
}



public BookABoatDto getBookABoatDto() {
 BookABoatDto bookABoatDto =new BookABoatDto();
 bookABoatDto.setId(id);
 bookABoatDto.setFromDate(fromDate);
 bookABoatDto.setToDate(toDate);
 bookABoatDto.setBookBoatStatus(bookBoatStatus);
bookABoatDto.setPrice(price);
bookABoatDto.setUserId(user.getId());
bookABoatDto.setBoatId(boat.getId());
bookABoatDto.setEmail(user.getEmail());
bookABoatDto.setUsername(user.getName());
return bookABoatDto;


}
 }

