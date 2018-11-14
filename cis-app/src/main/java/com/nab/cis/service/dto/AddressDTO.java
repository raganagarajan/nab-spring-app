package com.nab.cis.service.dto;

import javax.validation.constraints.NotBlank;

public class AddressDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
    private Integer unitNumber;
	
	@NotBlank
    private String streetName;
	
	@NotBlank
    private String suburb;
	
	@NotBlank
    private CityDTO city;
	
	@NotBlank
    private Integer pinCode;
	
	public Integer getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(Integer unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	
	public CityDTO getCity() {
		return city;
	}

	public void setCity(CityDTO city) {
		this.city = city;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}
}
