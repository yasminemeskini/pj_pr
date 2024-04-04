package com.example.webdevboat.yacht.controller;

import com.example.webdevboat.yacht.dto.BoatDto;
import com.example.webdevboat.yacht.dto.BookABoatDto;
import com.example.webdevboat.yacht.services.BoatService;
import com.example.webdevboat.yacht.services.CustomerService;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/api/admin")
public class BoatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoatController.class);

    @Autowired
    private BoatService boatService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/boat")
    public ResponseEntity<String> postBoat(@RequestParam("img") MultipartFile image,
                                           @RequestParam("brandName") String brandName,
                                           @RequestParam("price") int price,
                                           @RequestParam("description") String description,
                                           @RequestParam("port") String port,
                                           @RequestParam("boatName") String boatName,
                                           @RequestParam("boatType") String boatType) {
        try {
            if (image == null || image.isEmpty()) {
                return ResponseEntity.badRequest().body("Image is required");
            }

            BoatDto boatDto = new BoatDto();
            boatDto.setBrandName(brandName);
            boatDto.setBoatName(boatName);
            boatDto.setDescription(description);
            boatDto.setBoatType(boatType);
            boatDto.setPort(port);
            boatDto.setPrice(price);
            boatDto.setImage(image.getBytes());

            boolean result = boatService.postBoat(boatDto);

            if (result) {
                return ResponseEntity.ok("Boat posted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to post boat");
            }
        } catch (Exception e) {
            LOGGER.error("Error processing the boat post request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the boat post request");
        }
    }

  @GetMapping("/boats")
  public ResponseEntity<List<BoatDto>> getAllBoats() {
      try {
          List<BoatDto> boats = boatService.getAllBoats();
          return ResponseEntity.ok(boats);
      } catch (Exception e) {
          LOGGER.error("Error fetching all boats", e);
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
      }
  }

  @DeleteMapping("/boat/{id}")
  public ResponseEntity<String> deleteBoat(@PathVariable Long id) {
      try {
          boolean result = boatService.deleteBoat(id);
          if (result) {
              return ResponseEntity.ok("Boat deleted successfully");
          } else {
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Boat not found");
          }
      } catch (Exception e) {
          LOGGER.error("Error while processing boat deletion request", e);
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting boat");
      }
  }
  
  @GetMapping("/boat/{id}")
  public ResponseEntity <BoatDto>getBoatById(@PathVariable Long id){
	  
	  BoatDto boatDto = boatService.getBoatById(id);
	  return ResponseEntity.ok(boatDto);
	  
	  
  }
  
  
  @PutMapping("/boat/{id}")
  public ResponseEntity<String> updateBoat(
          @PathVariable Long id,
          @RequestParam(name = "img", required = false) MultipartFile image,
          @RequestParam("brandName") String brandName,
          @RequestParam("price") int price,
          @RequestParam("description") String description,
          @RequestParam("port") String port,
          @RequestParam("boatName") String boatName,
          @RequestParam("boatType") String boatType) {

      try {
          // Initialisez l'objet BoatDto
          BoatDto boatDto = new BoatDto();
          boatDto.setId(id); // Assurez-vous que votre BoatDto a une propriété pour l'ID
          boatDto.setBrandName(brandName);
          boatDto.setBoatName(boatName);
          boatDto.setDescription(description);
          boatDto.setBoatType(boatType);
          boatDto.setPort(port);
          boatDto.setPrice(price);

          if (image != null && !image.isEmpty()) {
              boatDto.setImage(image.getBytes());
          }

          boolean result = boatService.updateBoat(boatDto);

          if (result) {
              return ResponseEntity.ok("Boat updated successfully");
          } else {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update boat");
          }
      } catch (IOException e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the boat update request");
      }
  }
  
      
  @PostMapping("/book")
  public ResponseEntity<Void> bookABoat(@RequestBody BookABoatDto bookABoatDto) {
      try {
          // Vérifiez si les ID du bateau et de l'utilisateur sont présents
          if (bookABoatDto.getBoatId() == null || bookABoatDto.getUserId() == null) {
              return ResponseEntity.badRequest().build(); // Retourne un mauvais demande si les ID sont manquants
          }

          // Essayez de réserver le bateau
          boolean success = customerService.bookABoat(bookABoatDto);

          // Vérifiez si la réservation a réussi
          if (success) {
              return ResponseEntity.status(HttpStatus.CREATED).build(); // Renvoie une réponse 201 si la réservation est réussie
          } else {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Renvoie une erreur interne du serveur si la réservation a échoué
          }
      } catch (Exception e) {
          LOGGER.error("Error while processing boat booking request", e);
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Renvoie une erreur interne du serveur si une exception se produit
      }
  }
  
  
  @GetMapping("/boat/bookings")
  public ResponseEntity<List <BookABoatDto>>getBookings() {
  return  ResponseEntity.ok(boatService.getBookings());
  
  }
  
  @GetMapping("/boat/booking/{bookingId}/{status}")
  public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId, @PathVariable String status) {
      boolean success = boatService.changeBookingStatus(bookingId, status);
      if (success) {
          return ResponseEntity.ok().build();
      } else {
          return ResponseEntity.notFound().build();
      }
  }


  }
  
  
