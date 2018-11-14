package com.nab.cis.service;

import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nab.cis.domain.Address;
import com.nab.cis.domain.Country;
import com.nab.cis.repository.AddressRepository;
import com.nab.cis.repository.CityRepository;
import com.nab.cis.repository.CountryRepository;
import com.nab.cis.repository.StateRepository;
import com.nab.cis.service.dto.AddressDTO;
import com.nab.cis.service.dto.CityDTO;
import com.nab.cis.service.dto.CountryDTO;
import com.nab.cis.service.dto.StateDTO;

@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {
	
	private final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
	
	private CountryRepository countryRepository;
	
	private StateRepository stateRepository;
	
	private CityRepository cityRepository;
	
	private AddressRepository addressRepository;
	
	private DozerBeanMapper dozerBeanMapper;

	public AddressServiceImpl(CountryRepository countryRepository,
			StateRepository stateRepository,
			CityRepository cityRepository,
			AddressRepository addressRepository,
			DozerBeanMapper dozerBeanMapper) {
		super();
		this.countryRepository = countryRepository;
		this.stateRepository = stateRepository;
		this.cityRepository = cityRepository;
		this.addressRepository = addressRepository;
		this.dozerBeanMapper = dozerBeanMapper;
	}
	
	@Override
	public Page<CountryDTO> getAllCountries(Pageable pageable) {
		return countryRepository.findAll(pageable)
				.map(country -> {
					return dozerBeanMapper.map(country, CountryDTO.class);
				});
	}
	
	@Override
	public Page<StateDTO> getAllStates(Pageable pageable) {
		return stateRepository.findAll(pageable)
				.map(state -> {
					return dozerBeanMapper.map(state, StateDTO.class);
				});
	}
	
	@Override
	public Page<CityDTO> getAllCities(Pageable pageable) {
		return cityRepository.findAll(pageable)
				.map(city -> {
					return dozerBeanMapper.map(city, CityDTO.class);
				});
	}

	@Override
	public Optional<CountryDTO> getByCountry(String name) {
		return Optional.of(countryRepository.findOneByNameIgnoreCase(name))
				.filter(Optional::isPresent)
	            .map(Optional::get)
	            .map(country -> {
	            	return dozerBeanMapper.map(country, CountryDTO.class);
	            });
	}
	
	@Override
	public Optional<StateDTO> getByState(String name) {
		return Optional.of(stateRepository.findOneByNameIgnoreCase(name))
				.filter(Optional::isPresent)
	            .map(Optional::get)
	            .map(state -> {
	            	return dozerBeanMapper.map(state, StateDTO.class);
	            });
	}
	
	@Override
	public Optional<CityDTO> getByCity(String name) {
		return Optional.of(cityRepository.findOneByNameIgnoreCase(name))
				.filter(Optional::isPresent)
	            .map(Optional::get)
	            .map(country -> {
	            	return dozerBeanMapper.map(country, CityDTO.class);
	            });
	}

	@Override
	public CountryDTO createCountry(CountryDTO countryDTO) {
		Country newCountry = null;
		if(countryDTO.getId() == null) {
			//throw
		} else {
			newCountry = dozerBeanMapper.map(countryDTO, Country.class);
			countryRepository.save(newCountry);
		}
		logger.debug("Created new Country: {}", newCountry);
		return dozerBeanMapper.map(newCountry, CountryDTO.class);
	}

	@Override
	public void deleteAllCountries() {
		countryRepository.deleteAll();
	}

	@Override
	public AddressDTO createAddress(AddressDTO addressDTO) {
		Address newAddress = null;
		if(addressDTO.getId() != null) {
			//throw
		} else {
			newAddress = dozerBeanMapper.map(addressDTO, Address.class);
			addressRepository.save(newAddress);
		}
		logger.debug("Created new Address: {}", newAddress);
		return dozerBeanMapper.map(newAddress, AddressDTO.class);
	}
}
