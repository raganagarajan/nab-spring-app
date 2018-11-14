package com.nab.cis.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;
	
	@Size(max = 50)
    @Column(name = "middle_name", length = 50)
    private String middleName;
	
	@NotNull
	@Size(max = 50)
    @Column(name = "surname", length = 50)
    private String surname;
	
	@Size(max = 3)
    @Column(name = "initial", length = 3)
    private String initial;
	
	@Size(max = 50)
    @Column(name = "title", length = 50)
    private String title;
	
	@NotNull
	@Size(max = 50)
    @Column(name = "gender", length = 50)
    private String gender;
	
	@Size(max = 20)
    @Column(name = "marital_status", length = 20)
    private String maritalStatus;
	
	@NotNull
    @Column(name = "credit_rating", precision = 3, nullable = false)
    private Integer creditRating = 0;
	
	@NotNull
    @Column(name = "nab_customer", nullable = false)
    private boolean nabCustomer = false;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
	private List<Address> address;

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

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass()) {
            return false;
		}
		Customer other = (Customer) obj;
		return !(other.getId() == null || getId() == null) && Objects.equals(getId(), other.getId());
	}

	@Override
	public String toString() {
		return "Customer [id=" + getId() + ", firstName=" + firstName + ", middleName=" + middleName + ", surname=" + surname
				+ ", initial=" + initial + ", title=" + title + ", gender=" + gender + ", maritalStatus="
				+ maritalStatus + ", creditRating=" + creditRating + ", nabCustomer=" + nabCustomer + ", address="
				+ address + "]";
	}
}
