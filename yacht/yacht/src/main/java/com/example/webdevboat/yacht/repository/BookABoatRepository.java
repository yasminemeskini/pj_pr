package com.example.webdevboat.yacht.repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webdevboat.yacht.model.Boat;
import com.example.webdevboat.yacht.model.BookABoat;
@Repository
public interface BookABoatRepository extends JpaRepository<BookABoat,Long>{
	
	List<BookABoat>findAllByUserId(Long userId);
}

/*List<BookABoat>findAllByUserId(Long userId);*/

