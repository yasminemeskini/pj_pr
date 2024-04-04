package com.example.webdevboat.yacht.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.webdevboat.yacht.dto.BoatDto;
import com.example.webdevboat.yacht.dto.BookABoatDto;
import com.example.webdevboat.yacht.model.User;
import com.example.webdevboat.yacht.repository.UserRepository;

public interface BoatService {

	boolean postBoat(BoatDto boatDto) throws IOException;
	List<BoatDto> getAllBoats();
	  boolean deleteBoat(Long id);
	  BoatDto getBoatById(Long id);
	  public boolean updateBoat(BoatDto boatDto);
	  List<BookABoatDto>getBookings();
	  boolean changeBookingStatus(Long bookingId,String status);
	  
}
