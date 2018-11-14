package com.nab.cis.service.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CountryDTO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(min = 1, max = 50)
	private String name;

    private List<StateDTO> states;
    
    public CountryDTO() {
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StateDTO> getStates() {
		return states;
	}

	public void setStates(List<StateDTO> states) {
		this.states = states;
	}

	@Override
	public String toString() {
		return "CountryDTO [id=" + getId() + ", name=" + name + "]";
	}
}
