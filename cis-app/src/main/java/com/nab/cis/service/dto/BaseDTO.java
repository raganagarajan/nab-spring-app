package com.nab.cis.service.dto;

public abstract class BaseDTO extends BaseAuditDTO {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
