package com.nab.cis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nab.cis.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
	
	Optional<Country> findOneByNameIgnoreCase(String name);

}
