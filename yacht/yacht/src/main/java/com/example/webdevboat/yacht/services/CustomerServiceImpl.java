package com.example.webdevboat.yacht.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webdevboat.yacht.dto.BookABoatDto;
import com.example.webdevboat.yacht.model.Boat;
import com.example.webdevboat.yacht.model.BookABoat;
import com.example.webdevboat.yacht.model.BookBoatStatus;
import com.example.webdevboat.yacht.model.User;
import com.example.webdevboat.yacht.repository.BoatRepository;
import com.example.webdevboat.yacht.repository.BookABoatRepository;
import com.example.webdevboat.yacht.repository.UserRepository;



    @Service
    public class CustomerServiceImpl implements CustomerService {

        private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

        @Autowired
        private BoatRepository boatRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private BookABoatRepository bookABoatRepository;


       



        @Override
        public List<BookABoatDto> getBookingsByUserId(Long userId) {
            return bookABoatRepository.findAllByUserId(userId).stream()
                    .map(BookABoat::getBookABoatDto)
                    .collect(Collectors.toList());
        }






	@Override
	public boolean bookABoat(BookABoatDto bookABoatDto) {
		  try {
              // Vérifier si fromDate et toDate sont null
              if (bookABoatDto.getFromDate() == null || bookABoatDto.getToDate() == null) {
                  LOGGER.error("FromDate or ToDate is null in BookABoatDto");
                  return false; // Retourner false si l'une des dates est null
              }

              // Convertir les chaînes de caractères en objets Date
              DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
              Date fromDate = dateFormat.parse(bookABoatDto.getFromDate());
              Date toDate = dateFormat.parse(bookABoatDto.getToDate());

              // Recherche du bateau et de l'utilisateur dans la base de données
              Optional<Boat> optionalBoat = boatRepository.findById(bookABoatDto.getBoatId());
              Optional<User> optionalUser = userRepository.findById(bookABoatDto.getUserId());

              // Vérifiez si le bateau et l'utilisateur existent dans la base de données
              if (optionalBoat.isPresent() && optionalUser.isPresent()) {
                  Boat existingBoat = optionalBoat.get();

                  // Crée une nouvelle réservation de bateau
                  BookABoat bookABoat = new BookABoat();
                  bookABoat.setUser(optionalUser.get());
                  bookABoat.setBoat(existingBoat);
                  bookABoat.setBookBoatStatus(BookBoatStatus.PENDING); // Définir le statut sur PENDING
                  bookABoat.setFromDate(bookABoatDto.getFromDate());
                  bookABoat.setToDate(bookABoatDto.getToDate());

                  // Calcul de la durée de la réservation
                  long diffInMilliSeconds = toDate.getTime() - fromDate.getTime();
                  long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSeconds);
                  bookABoat.setDays(days);

                  // Calcul du prix de la réservation
                  bookABoat.setPrice((int) (existingBoat.getPrice() * days));

                  // Enregistre la réservation dans la base de données
                  bookABoatRepository.save(bookABoat);
                  return true; // Retourne true si la réservation est réussie
              } else {
                  return false;
              }
          } catch (Exception e) {
              LOGGER.error("Error while booking boat", e);
              return false; // Retourne false si une exception se produit
          }
}
}