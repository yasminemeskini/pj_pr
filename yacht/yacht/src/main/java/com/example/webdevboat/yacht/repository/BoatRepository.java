package com.example.webdevboat.yacht.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webdevboat.yacht.model.Boat;
@Repository

public interface BoatRepository extends JpaRepository<Boat, Long> {
    Optional<Boat> findById(Long id);

}
