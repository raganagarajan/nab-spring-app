package com.nab.cis.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Size(max = 20)
    @Column(name = "type", length = 20)
	private String type;
	
    @Column(name = "unit_number")
    private Integer unitNumber;
	
	@Size(max = 50)
    @Column(name = "street_name", length = 50)
    private String streetName;
	
	@Size(max = 50)
    @Column(name = "suburb", length = 50)
    private String suburb;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
    private City city;
	
    @Column(name = "pin_code")
    private Integer pinCode;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
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
		Address other = (Address) obj;
		return !(other.getId() == null || getId() == null) && Objects.equals(getId(), other.getId());
	}

	@Override
	public String toString() {
		return "Address [id=" + getId() + ", unitNumber=" + unitNumber + ", streetName=" + streetName + ", suburb=" + suburb
				+ ", city=" + city + ", pinCode=" + pinCode + "]";
	}
}
