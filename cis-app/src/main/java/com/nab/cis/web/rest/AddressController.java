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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nab.cis.service.AddressService;
import com.nab.cis.service.dto.AddressDTO;
import com.nab.cis.service.dto.CityDTO;
import com.nab.cis.service.dto.CountryDTO;
import com.nab.cis.service.dto.StateDTO;
import com.nab.cis.web.rest.util.PaginationUtil;
import com.nab.cis.web.rest.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class AddressController {
	
	private final Logger logger = LoggerFactory.getLogger(AddressController.class);
	
	private final AddressService addressService;

	public AddressController(AddressService addressService) {
		super();
		this.addressService = addressService;
	} 
	
	@PostMapping("/address")
	public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
		logger.debug("REST request to save Address : {}", addressDTO);
		AddressDTO newAddress = addressService.createAddress(addressDTO);
		return new ResponseEntity<>(newAddress, null, HttpStatus.CREATED);
	}
	
	@GetMapping("/address/countries")
    public ResponseEntity<List<CountryDTO>> getAllCountries(Pageable pageable) {
        final Page<CountryDTO> page = addressService.getAllCountries(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/address/countries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
	
	@GetMapping("/address/states")
    public ResponseEntity<List<StateDTO>> getAllStates(Pageable pageable) {
        final Page<StateDTO> page = addressService.getAllStates(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/address/states");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
	
	@GetMapping("/address/cities")
    public ResponseEntity<List<CityDTO>> getAllCities(Pageable pageable) {
        final Page<CityDTO> page = addressService.getAllCities(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/address/cities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
	
	@GetMapping("/address/countries/{countryName}")
    public ResponseEntity<CountryDTO> getByCountry(@PathVariable(required = false) String countryName) {
        final Optional<CountryDTO> content = addressService.getByCountry(countryName);
        return ResponseUtil.wrapOrNotFound(content);
    }
}
