package com.example.webdevboat.yacht.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webdevboat.yacht.enums.UserRole;
import com.example.webdevboat.yacht.model.User;




@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findFirstByEmail(String email);

	User findByUserRole(UserRole admin);


}