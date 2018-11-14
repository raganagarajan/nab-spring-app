package com.nab.cis.web.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nab.cis.service.CustomerService;
import com.nab.cis.service.dto.CustomerDTO;
import com.nab.cis.web.rest.util.PaginationUtil;
import com.nab.cis.web.rest.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	private final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	} 
	
	@GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(Pageable pageable) {
        final Page<CustomerDTO> page = customerService.getAllCustomers(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
	
	@PostMapping("/customers")
	public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
		logger.debug("REST request to save Customer : {}", customerDTO);
		CustomerDTO newCustomer = customerService.createCustomer(customerDTO);
		return new ResponseEntity<>(newCustomer, null, HttpStatus.CREATED);
	}
	
	@PutMapping("/customers")
	public ResponseEntity<CustomerDTO> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
		logger.debug("REST request to update Customer : {}", customerDTO);
		Optional<CustomerDTO> updatedCustomer = customerService.updateCustomer(customerDTO);
		return ResponseUtil.wrapOrNotFound(updatedCustomer);
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable(required = true) Long id) {
		logger.debug("REST request to delete Customer : {}", id);
		customerService.deleteCustomer(id);
		return ResponseEntity.ok().build();
	}
}
