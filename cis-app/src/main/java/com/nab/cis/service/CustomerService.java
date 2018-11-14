package com.nab.cis.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nab.cis.service.dto.CustomerDTO;

public interface CustomerService {

	Page<CustomerDTO> getAllCustomers(Pageable pageable);
	Optional<CustomerDTO> getCustomer(Long id);
	CustomerDTO createCustomer(CustomerDTO customerDTO);
	Optional<CustomerDTO> updateCustomer(CustomerDTO customerDTO);
	void deleteCustomer(Long id);
}
