package com.nab.cis.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "state")
public class State extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;
	
	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;
	
	@OneToMany(mappedBy = "state", cascade = CascadeType.REMOVE)
	private List<City> cities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
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
		State other = (State) obj;
		return !(other.getId() == null || getId() == null) && Objects.equals(getId(), other.getId());
	}

	@Override
	public String toString() {
		return "State [id=" + getId() + ", name=" + name + "]";
	}
}
