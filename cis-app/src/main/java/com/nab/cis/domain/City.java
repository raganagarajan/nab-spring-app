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
@Table(name = "city")
public class City extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;
	
	@ManyToOne
	@JoinColumn(name = "state_id", nullable = false)
	private State state;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
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
		City other = (City) obj;
		return !(other.getId() == null || getId() == null) && Objects.equals(getId(), other.getId());
	}

	@Override
	public String toString() {
		return "City [id=" + getId() + ", name=" + name + "]";
	}
}
