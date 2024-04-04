package com.example.webdevboat.yacht.services;


import java.util.List;

import com.example.webdevboat.yacht.dto.BookABoatDto;
public interface CustomerService {
boolean bookABoat(BookABoatDto bookABoatDto);
List<BookABoatDto>getBookingsByUserId(Long userId);

}
