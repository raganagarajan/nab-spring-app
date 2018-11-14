package com.nab.cis.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CityDTO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(min = 1, max = 50)
	private String name;
	
	@NotBlank
	@JsonIgnore
    private StateDTO state;

    public CityDTO() {
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StateDTO getState() {
		return state;
	}

	public void setState(StateDTO state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "CityDTO [id=" + getId() + ", name=" + name + "]";
	}
}
