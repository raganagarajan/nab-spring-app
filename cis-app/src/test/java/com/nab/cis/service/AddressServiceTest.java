package com.nab.cis.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nab.cis.CISApplication;
import com.nab.cis.domain.City;
import com.nab.cis.domain.Country;
import com.nab.cis.domain.Customer;
import com.nab.cis.domain.State;
import com.nab.cis.service.dto.CityDTO;
import com.nab.cis.service.dto.CountryDTO;
import com.nab.cis.service.dto.CustomerDTO;
import com.nab.cis.service.dto.StateDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CISApplication.class)
@Transactional
public class AddressServiceTest {

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	private Country country1, country2;
	
	private State state1, state2;
	
	private City city1, city2, city3;
	
	@Before
    public void init() {
		country1 = new Country();
		country1.setName("Australia");
		city1 = new City();
		city1.setName("Melbourne");
		city2 = new City();
		city2.setName("Geelong");
		List<City> cities = new ArrayList<>();
		cities.add(city1);
		cities.add(city2);
		state1 = new State();
		state1.setName("Victoria");
		state1.setCities(cities);
		List<State> states = new ArrayList<>();
		states.add(state1);
		country1.setStates(states);
		
		country2 = new Country();
		country2.setName("Singapore");
		city3 = new City();
		city3.setName("Sydney");
		List<City> cities2 = new ArrayList<>();
		cities2.add(city3);
		state2 = new State();
		state2.setName("NSW");
		state2.setCities(cities2);
		List<State> states2 = new ArrayList<>();
		states2.add(state2);
		country2.setStates(states2);
    }
	
	@Test
    @Transactional
	public void assertThatAllCountriesCanBeFound() {
		Page<CountryDTO> actualCountries = addressService.getAllCountries(PageRequest.of(0, 10));
		assertThat(actualCountries).isNotNull();
		assertThat(actualCountries.getContent()).hasSize(2);
	}
	
	@Test
    @Transactional
	public void assertThatTwoCountriesAreNotEqual() {
		Page<CountryDTO> actualCountries = addressService.getAllCountries(PageRequest.of(0, 10));
		assertThat(actualCountries).isNotNull();
		List<CountryDTO> actualCountryList = actualCountries.getContent();
		assertThat(actualCountryList.size()).isGreaterThanOrEqualTo(2);
		Country actualCountry1 = dozerBeanMapper.map(actualCountryList.get(0), Country.class);
		Country actualCountry2 = dozerBeanMapper.map(actualCountryList.get(1), Country.class);
		assertThat(actualCountry1).isNotNull();
		assertThat(actualCountry2).isNotNull();
		assertThat(actualCountry1.equals(actualCountry2)).isFalse();

	}
	
	@Test
    @Transactional
	public void assertThatAllStatesCanBeFound() {
		Page<StateDTO> actualStates = addressService.getAllStates(PageRequest.of(0, 10));
		assertThat(actualStates).isNotNull();
		assertThat(actualStates.getContent()).hasSize(4);
	}
	
	@Test
    @Transactional
	public void assertThatTwoStatesAreNotEqual() {
		Page<StateDTO> actualStates = addressService.getAllStates(PageRequest.of(0, 10));
		assertThat(actualStates).isNotNull();
		List<StateDTO> actualStateList = actualStates.getContent();
		assertThat(actualStateList.size()).isGreaterThanOrEqualTo(2);
		State actualState1 = dozerBeanMapper.map(actualStateList.get(0), State.class);
		State actualState2 = dozerBeanMapper.map(actualStateList.get(1), State.class);
		assertThat(actualState1).isNotNull();
		assertThat(actualState2).isNotNull();
		assertThat(actualState1.equals(actualState2)).isFalse();
	}
	
	@Test
    @Transactional
	public void assertThatAllCitiesCanBeFound() {
		Page<CityDTO> actualcities = addressService.getAllCities(PageRequest.of(0, 10));
		assertThat(actualcities).isNotNull();
		assertThat(actualcities.getContent()).hasSize(4);
	}
	
	@Test
    @Transactional
	public void assertThatTwoCitiesAreNotEqual() {
		Page<CityDTO> actualCities = addressService.getAllCities(PageRequest.of(0, 10));
		assertThat(actualCities).isNotNull();
		List<CityDTO> actualCityList = actualCities.getContent();
		assertThat(actualCityList.size()).isGreaterThanOrEqualTo(2);
		City actualCity1 = dozerBeanMapper.map(actualCityList.get(0), City.class);
		City actualCity2 = dozerBeanMapper.map(actualCityList.get(1), City.class);
		assertThat(actualCity1).isNotNull();
		assertThat(actualCity2).isNotNull();
		assertThat(actualCity1.equals(actualCity2)).isFalse();
	}
	
	@Test
    @Transactional
	public void assertThatCountryCanBeFound() {
		Optional<CountryDTO> actualCountry = addressService.getByCountry("Singapore");
		assertThat(actualCountry).isNotNull();
		assertThat(actualCountry.get().getName()).isEqualTo("Singapore");
	}
	
	@Test
    @Transactional
	public void assertThatStateCanBeFound() {
		Optional<StateDTO> actualState = addressService.getByState("NSW");
		assertThat(actualState).isNotNull();
		assertThat(actualState.get().getName()).isEqualTo("NSW");
	}
	
	@Test
    @Transactional
	public void assertThatCityCanBeFound() {
		Optional<CityDTO> actualcity = addressService.getByCity("Sydney");
		assertThat(actualcity).isNotNull();
		assertThat(actualcity.get().getName()).isEqualTo("Sydney");
	}
}
