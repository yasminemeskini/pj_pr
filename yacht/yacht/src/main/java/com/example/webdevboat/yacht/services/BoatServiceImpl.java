package com.example.webdevboat.yacht.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.webdevboat.yacht.dto.BoatDto;
import com.example.webdevboat.yacht.dto.BookABoatDto;
import com.example.webdevboat.yacht.model.Boat;
import com.example.webdevboat.yacht.model.BookABoat;
import com.example.webdevboat.yacht.model.BookBoatStatus;
import com.example.webdevboat.yacht.repository.BoatRepository;
import com.example.webdevboat.yacht.repository.BookABoatRepository;

import lombok.RequiredArgsConstructor;

import java.awt.print.Book;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class BoatServiceImpl implements BoatService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BoatServiceImpl.class);

    @Autowired
    private BoatRepository boatRepository;
    @Autowired
    private BookABoatRepository bookABoatRepository;

    @Override
    public boolean postBoat(BoatDto boatDto) {
        try {
            LOGGER.info("Processing boat post request in BoatServiceImpl");

            // Vérifiez si l'image est présente
            if (boatDto.getImage() != null && boatDto.getImage().length > 0) {
                // Utilisez directement le tableau de bytes de l'objet MultipartFile
                byte[] imageBytes = boatDto.getImage();

                // Créez un objet Boat et définissez ses propriétés
                Boat boat = new Boat();
                boat.setBrandName(boatDto.getBrandName());
                boat.setBoatName(boatDto.getBoatName());
                boat.setDescription(boatDto.getDescription());
                boat.setBoatType(boatDto.getBoatType());
                boat.setPort(boatDto.getPort());
                boat.setPrice(boatDto.getPrice());
                boat.setImage(imageBytes);

                LOGGER.info("Saving boat to repository");

                // Enregistrez l'objet Boat dans la base de données
                boatRepository.save(boat);

                LOGGER.info("Boat saved successfully");
            } else {
                // Gérez le cas où l'image est manquante
                LOGGER.warn("Image is missing. Boat not saved.");
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.error("Error while processing boat post request in BoatServiceImpl", e);
            return false;
        }
    }

	@Override
	public List<BoatDto> getAllBoats() {
		return boatRepository.findAll().stream().map(Boat::getBoatDto).collect(Collectors.toList());
	}

	@Override
	public boolean deleteBoat(Long id) {
		 try {
	            boatRepository.deleteById(id);
	            return true;
	        } catch (EmptyResultDataAccessException e) {
	            // Gérez le cas où le bateau avec l'ID spécifié n'est pas trouvé
	            return false;
	        } catch (Exception e) {
	            LOGGER.error("Error while deleting boat in BoatServiceImpl", e);
	            return false;
	        }
	    }

	@Override
	public BoatDto getBoatById(Long id) {
Optional<Boat> optinalBoat = boatRepository.findById(id);
return optinalBoat.map(Boat::getBoatDto).orElse(null);
	}

	@Override
		public boolean updateBoat(BoatDto boatDto) {
	        try {
	            Optional<Boat> optionalBoat = boatRepository.findById(boatDto.getId());

	            if (optionalBoat.isPresent()) {
	                Boat existingBoat = optionalBoat.get();
	                existingBoat.setBrandName(boatDto.getBrandName());
	                existingBoat.setBoatName(boatDto.getBoatName());
	                existingBoat.setDescription(boatDto.getDescription());
	                existingBoat.setBoatType(boatDto.getBoatType());
	                existingBoat.setPort(boatDto.getPort());
	                existingBoat.setPrice(boatDto.getPrice());

	                if (boatDto.getImage() != null) {
	                    existingBoat.setImage(boatDto.getImage());
	                }

	                boatRepository.save(existingBoat);
	                return true;
	            } else {
	                return false; // Bateau non trouvé
	            }
	        } catch (Exception e) {
	            LOGGER.error("Error while processing boat update request in BoatServiceImpl", e);
	            return false;
	        }
	    }

	@Override
	public List<BookABoatDto> getBookings() {
		// TODO Auto-generated method stub
		return bookABoatRepository.findAll().stream().map(BookABoat::getBookABoatDto).collect(Collectors.toList());
	}

	@Override
	public boolean changeBookingStatus(Long bookingId, String status) {
        Optional<BookABoat> optionalBookABoat = bookABoatRepository.findById(bookingId);
        if (optionalBookABoat.isPresent()) {
        	BookABoat existingBookABoat = optionalBookABoat.get();
        if (Objects.equals(status, "Approve"))
        	existingBookABoat.setBookBoatStatus(BookBoatStatus.APPROVED);
        else
        	existingBookABoat.setBookBoatStatus(BookBoatStatus.REJECTED);
        bookABoatRepository.save(existingBookABoat);
        return true;
        
        }
        
        return false;
	}

    }
  
        

	
	
	
	
	
	
	
	
	
	
	




















    

   /* @Override
    public List<Boat> getAllBoats() {
        return boatRepository.findAll();
    }

    @Override
    public Boat getBoatById(Long id) {
        return boatRepository.findById(id).orElse(null);
    

    @Override
    public Boat createBoat(MultipartFile image, String brandName, String boatName, String description,
                           String boatType, String port, double price) throws IOException {
        // Logic for handling image upload, saving boat details, etc.
        // Example: Saving the image to a folder and creating a new Boat object
        byte[] imageBytes = image.getBytes();
        Boat boat = new Boat();
        boat.setBrandName(brandName);
        boat.setBoatName(boatName);
        boat.setDescription(description);
        boat.setBoatType(boatType);
        boat.setPort(port);
        boat.setPrice(price);
        boat.setImage(imageBytes);

        return boatRepository.save(boat);
    }
    */
