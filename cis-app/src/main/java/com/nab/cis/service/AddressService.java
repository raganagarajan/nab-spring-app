package com.nab.cis.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nab.cis.service.dto.AddressDTO;
import com.nab.cis.service.dto.CityDTO;
import com.nab.cis.service.dto.CountryDTO;
import com.nab.cis.service.dto.StateDTO;

public interface AddressService {

	Page<CountryDTO> getAllCountries(Pageable pageable);
	Page<StateDTO> getAllStates(Pageable pageable);
	Page<CityDTO> getAllCities(Pageable pageable);
	Optional<CountryDTO> getByCountry(String name);
	Optional<StateDTO> getByState(String name);
	Optional<CityDTO> getByCity(String name);
	CountryDTO createCountry(CountryDTO countryDTO);
	AddressDTO createAddress(AddressDTO addressDTO);
	void deleteAllCountries();
}
