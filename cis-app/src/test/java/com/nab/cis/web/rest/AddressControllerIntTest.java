package com.nab.cis.web.rest;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.nab.cis.CISApplication;
import com.nab.cis.domain.Country;
import com.nab.cis.repository.CountryRepository;
import com.nab.cis.service.AddressService;
import com.nab.cis.service.dto.CityDTO;
import com.nab.cis.service.dto.CountryDTO;
import com.nab.cis.service.dto.StateDTO;
import com.nab.cis.web.rest.exception.WebExceptionTranslator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CISApplication.class)
public class AddressControllerIntTest {
	
	 @Autowired
	 private AddressService addressService;
	 
	 @Autowired
	 private CountryRepository countryRepository;
	 
	 @Autowired
	 private MappingJackson2HttpMessageConverter jacksonMessageConverter;
	 
	 @Autowired
	 private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
	 
	 @Autowired
	 private WebExceptionTranslator webExceptionTranslator;
	 
	 @Autowired
	 private DozerBeanMapper dozerBeanMapper;
	 
	 private MockMvc restAddressMockMvc;
	 
	 private static final String DEFAULT_COUNTRY_NAME1 = "Russia";
	 private static final String DEFAULT_COUNTRY_NAME2 = "France";
	 private static final String DEFAULT_COUNTRY_NAME3 = "Singapore";
	 private static final String DEFAULT_CITY_NAME1 = "Moscow";
	 private static final String DEFAULT_CITY_NAME2 = "Paris";
	 private static final String DEFAULT_CITY_NAME3 = "Sydney";
	 private static final String DEFAULT_STATE_NAME1 = "Moscow";
	 private static final String DEFAULT_STATE_NAME2 = "Paris";
	 private static final String DEFAULT_STATE_NAME3 = "Victoria";
	 
	 @Before
	 public void setup() {
		 AddressController controller = new AddressController(addressService);
		 this.restAddressMockMvc = MockMvcBuilders.standaloneSetup(controller)
	            .setCustomArgumentResolvers(pageableArgumentResolver)
	            .setControllerAdvice(webExceptionTranslator)
	            .setMessageConverters(jacksonMessageConverter)
	            .build();
	 }
	 
	 public static CountryDTO createCountryObj1() {
		 CountryDTO country = new CountryDTO();
		 country.setName(DEFAULT_COUNTRY_NAME1);
		 CityDTO city1 = new CityDTO();
		 city1.setName(DEFAULT_CITY_NAME1);
		 List<CityDTO> cities = new ArrayList<>();
		 cities.add(city1);
		 StateDTO state1 = new StateDTO();
		 state1.setName(DEFAULT_STATE_NAME1);
		 state1.setCities(cities);
		 List<StateDTO> states = new ArrayList<>();
		 states.add(state1);
		 country.setStates(states);
		 return country;
	 }
	 
	 public static CountryDTO createCountryObj2() {
		 CountryDTO country = new CountryDTO();
		 country.setName(DEFAULT_COUNTRY_NAME2);
		 CityDTO city1 = new CityDTO();
		 city1.setName(DEFAULT_CITY_NAME2);
		 List<CityDTO> cities = new ArrayList<>();
		 cities.add(city1);
		 StateDTO state1 = new StateDTO();
		 state1.setName(DEFAULT_STATE_NAME2);
		 state1.setCities(cities);
		 List<StateDTO> states = new ArrayList<>();
		 states.add(state1);
		 country.setStates(states);
		 return country;
	 }
	 
	 @Test
	 @Transactional
	 public void getAllCountries() throws Exception {
		 CountryDTO country = createCountryObj1();
		 countryRepository.saveAndFlush(dozerBeanMapper.map(country, Country.class));
		 country = createCountryObj2();
		 countryRepository.saveAndFlush(dozerBeanMapper.map(country, Country.class));
		 // Get all the customers
		 restAddressMockMvc.perform(get("/api/address/countries")
				.accept(MediaType.APPLICATION_JSON))
            	.andExpect(status().isOk())
            	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            	.andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_COUNTRY_NAME2)));
	 }
	 
	 @Test
	 @Transactional
	 public void getAllStates() throws Exception {
		 CountryDTO country = createCountryObj1();
		 countryRepository.saveAndFlush(dozerBeanMapper.map(country, Country.class));
		 country = createCountryObj2();
		 countryRepository.saveAndFlush(dozerBeanMapper.map(country, Country.class));
		 // Get all the customers
		 restAddressMockMvc.perform(get("/api/address/states")
				.accept(MediaType.APPLICATION_JSON))
            	.andExpect(status().isOk())
            	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            	.andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_STATE_NAME3)));
	 }
	 
	 @Test
	 @Transactional
	 public void getAllCities() throws Exception {
		 CountryDTO country = createCountryObj1();
		 countryRepository.saveAndFlush(dozerBeanMapper.map(country, Country.class));
		 country = createCountryObj2();
		 countryRepository.saveAndFlush(dozerBeanMapper.map(country, Country.class));
		 // Get all the customers
		 restAddressMockMvc.perform(get("/api/address/cities")
				.accept(MediaType.APPLICATION_JSON))
            	.andExpect(status().isOk())
            	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            	.andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_CITY_NAME3)));
	 }
}
