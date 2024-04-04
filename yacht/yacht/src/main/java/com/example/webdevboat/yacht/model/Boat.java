package com.example.webdevboat.yacht.model;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.example.webdevboat.yacht.dto.BoatDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "yachts")
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brandName;
    private String boatName;
    private String description;
    private String boatType;
    private String port;
    
    // Use a numeric type for price
    private int price;

    // Use the appropriate column definition for your database
    @Column(columnDefinition = "longblob")
    @JsonIgnore
    private byte[] image;
	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * @return the boatName
	 */
	public String getBoatName() {
		return boatName;
	}

	/**
	 * @param boatName the boatName to set
	 */
	public void setBoatName(String boatName) {
		this.boatName = boatName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the boatType
	 */
	public String getBoatType() {
		return boatType;
	}

	/**
	 * @param boatType the boatType to set
	 */
	public void setBoatType(String boatType) {
		this.boatType = boatType;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the price
	 */
	 public int getPrice() {
	        return price;
	    }

	    public void setPrice(int price) {
	        this.price = price;
	    }

	    public byte[] getImage() {
	        return image;
	    }

	    public void setImage(byte[] image) {
	        this.image = image;
	    }
	 
  	
	    
	    
/**
		 * @return the id
		 */
		

		/**
		 * @param id the id to set
		 */
		public void setId(Long id) {
			this.id = id;
		}

public BoatDto getBoatDto(){
	BoatDto boatDto = new BoatDto();
	boatDto.setId(id);
   boatDto.setBoatName(boatName);
   boatDto.setDescription(description);
   boatDto.setBrandName(brandName);
   boatDto.setBoatType(boatType);
   boatDto.setPort(port);
   boatDto.setPrice(price);
   boatDto.setImage(image);
	return boatDto;
	
}
  
public Long getId() {
	return id;
}	
	  
	    	
	    	
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
