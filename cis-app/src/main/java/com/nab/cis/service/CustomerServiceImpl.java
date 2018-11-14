package com.nab.cis.service;

import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nab.cis.domain.Customer;
import com.nab.cis.repository.CustomerRepository;
import com.nab.cis.service.dto.CustomerDTO;
import com.nab.cis.web.rest.exception.CustomerIdAlreadyFoundException;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	private CustomerRepository customerRepository;
	
	private DozerBeanMapper dozerBeanMapper;

	public CustomerServiceImpl(CustomerRepository customerRepository, 
		    DozerBeanMapper dozerBeanMapper) {
		super();
		this.customerRepository = customerRepository;
		this.dozerBeanMapper = dozerBeanMapper;
	}
	
	@Override
	public Page<CustomerDTO> getAllCustomers(Pageable pageable) {
		return customerRepository.findAll(pageable)
				.map(customer -> {
	            	return dozerBeanMapper.map(customer, CustomerDTO.class);
	            });
	}
	
	@Override
	public Optional<CustomerDTO> getCustomer(Long id) {
		return Optional.of(customerRepository
	            .findById(id))
	            .filter(Optional::isPresent)
	            .map(Optional::get)
	            .map(customer -> {
	            	return dozerBeanMapper.map(customer, CustomerDTO.class);
	            });
	}

	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDTO) {
		Customer newCustomer = null;
		if(customerDTO.getId() != null) {
			throw new CustomerIdAlreadyFoundException();
		} else {
			newCustomer = dozerBeanMapper.map(customerDTO, Customer.class);
			customerRepository.save(newCustomer);
		}
		logger.debug("Created new Customer: {}", newCustomer);
		return dozerBeanMapper.map(newCustomer, CustomerDTO.class);
	}
	
	@Override
	public Optional<CustomerDTO> updateCustomer(CustomerDTO customerDTO) {
		return Optional.of(customerRepository
	            .findById(customerDTO.getId()))
	            .filter(Optional::isPresent)
	            .map(Optional::get)
	            .map(customer -> {
	            	customer = dozerBeanMapper.map(customerDTO, Customer.class);
	                customerRepository.save(customer);
	                return customer;
	            })
	            .map(customer -> {
	            	return dozerBeanMapper.map(customer, CustomerDTO.class);
	            });
	}

	@Override
	public void deleteCustomer(Long id) {
		customerRepository.findById(id).ifPresent(customer -> {
			customerRepository.delete(customer);
			logger.debug("Deleted Country: {}", customer);
		});
	}
}
