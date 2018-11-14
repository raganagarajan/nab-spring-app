package com.nab.cis.service.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StateDTO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;

	@Size(min = 1, max = 50)
	private String name;
	
	@NotBlank
	@JsonIgnore
	private CountryDTO country;

    private List<CityDTO> cities;
    
    public StateDTO() {
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public CountryDTO getCountry() {
		return country;
	}

	public void setCountry(CountryDTO country) {
		this.country = country;
	}

	public List<CityDTO> getCities() {
		return cities;
	}

	public void setCities(List<CityDTO> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		return "StateDTO [id=" + getId() + ", name=" + name + "]";
	}
}
