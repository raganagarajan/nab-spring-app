package com.nab.cis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nab.cis.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
}
