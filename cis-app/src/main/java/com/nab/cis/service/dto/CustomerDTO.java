package com.nab.cis.service.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.nab.cis.domain.Address;
import com.nab.cis.domain.Customer;

public class CustomerDTO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;

	@NotBlank
    private String firstName;
	
    private String middleName;
	
    @NotBlank
    private String surname;
	
    private String initial;
	
    private String title;
	
    @NotBlank
    private String gender;
	
    @NotBlank
    private String maritalStatus;
	
    private Integer creditRating = 0;
	
    private boolean nabCustomer = false;
	
	private List<AddressDTO> address;

	public CustomerDTO() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getCreditRating() {
		return creditRating;
	}

	public void setCreditRating(Integer creditRating) {
		this.creditRating = creditRating;
	}

	public boolean isNabCustomer() {
		return nabCustomer;
	}

	public void setNabCustomer(boolean nabCustomer) {
		this.nabCustomer = nabCustomer;
	}

	public List<AddressDTO> getAddress() {
		return address;
	}

	public void setAddress(List<AddressDTO> address) {
		this.address = address;
	}
}
