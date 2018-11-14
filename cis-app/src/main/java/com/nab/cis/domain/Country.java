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
@Table(name = "country")
public class Country extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;
	
	@OneToMany(mappedBy = "country", cascade = CascadeType.REMOVE)
	private List<State> states;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
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
		Country other = (Country) obj;
		return !(other.getId() == null || getId() == null) && Objects.equals(getId(), other.getId());
	}

	@Override
	public String toString() {
		return "Country [id=" + getId() + ", name=" + name + "]";
	}
}
