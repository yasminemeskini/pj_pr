package com.example.webdevboat.yacht.dto;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;

public class BoatDto {
	private Long id;
    private String brandName;
    private String boatName;
    private String description;
    private String boatType;
    private String port;
    private int price;
    private byte[] image;


    // Ajoutez les getters et setters appropriés ici

	
	public String getBrandName() {
        return brandName;
    }

    /**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBoatName() {
        return boatName;
    }

    public void setBoatName(String boatName) {
        this.boatName = boatName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBoatType() {
        return boatType;
    }

    public void setBoatType(String boatType) {
        this.boatType = boatType;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }
    
}
