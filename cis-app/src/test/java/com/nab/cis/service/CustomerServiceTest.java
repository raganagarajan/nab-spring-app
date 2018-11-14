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
import com.nab.cis.domain.Address;
import com.nab.cis.domain.City;
import com.nab.cis.domain.Country;
import com.nab.cis.domain.Customer;
import com.nab.cis.domain.State;
import com.nab.cis.service.dto.AddressDTO;
import com.nab.cis.service.dto.CityDTO;
import com.nab.cis.service.dto.CustomerDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CISApplication.class)
@Transactional
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	private Customer customer1, customer2;
	
	private Address address1, address2;
	
	private Country country1, country2;
	
	private State state1, state2;
	
	private City city1, city2, city3;
	
	@Before
    public void init() {
		address1 = new Address();
		address1.setType("MAILING");
		address1.setUnitNumber(2001);
		address1.setStreetName("XYZ Street");
		address1.setSuburb("East Suburb");
		address1.setPinCode(3245);
		
		customer1 = new Customer();
		customer1.setFirstName("John");
		customer1.setMiddleName("Van");
		customer1.setSurname("Damme");
		customer1.setInitial("JVD");
		customer1.setTitle("Mr");
		customer1.setGender("Male");
		customer1.setMaritalStatus("NeverMarried");
		customer1.setCreditRating(50);
		customer1.setNabCustomer(true);
		
		address2 = new Address();
		address2.setType("RESIDENTIAL");
		address2.setUnitNumber(3401);
		address2.setStreetName("ABC Street");
		address2.setSuburb("Northern Suburb");
		address2.setPinCode(3200);
		address2.setCity(city1);
		List<Address> addresses2 = new ArrayList<>();
		addresses2.add(address2);
		
		customer2 = new Customer();
		customer2.setFirstName("Wendy");
		customer2.setMiddleName("Marvel");
		customer2.setSurname("Stark");
		customer2.setInitial("TMS");
		customer2.setTitle("Miss");
		customer2.setGender("Female");
		customer2.setMaritalStatus("Married");
		customer2.setCreditRating(45);
		customer2.setNabCustomer(false);
		customer2.setAddress(addresses2);
		
    }
	
	@Test
    @Transactional
	public void assertThatCustomerCanBeCreated() {
		CityDTO cityDTO = addressService.getByCity("Melbourne").get();
		address1.setCity(dozerBeanMapper.map(cityDTO, City.class));
		List<Address> addresses1 = new ArrayList<>();
		addresses1.add(address1);
		customer1.setAddress(addresses1);
		CustomerDTO customerDTO = dozerBeanMapper.map(customer1, CustomerDTO.class);
		CustomerDTO actualCustomer = customerService.createCustomer(customerDTO);
		assertThat(actualCustomer).isNotNull();
		assertThat(actualCustomer.getId()).isNotNull();
		assertThat(actualCustomer.getFirstName()).isEqualTo(customer1.getFirstName());
		assertThat(actualCustomer.getMiddleName()).isEqualTo(customer1.getMiddleName());
		assertThat(actualCustomer.getSurname()).isEqualTo(customer1.getSurname());
		assertThat(actualCustomer.getAddress()).hasSize(1);
	}
	
	@Test
    @Transactional
	public void assertThatCustomerCanBeUpdated() {
		CustomerDTO customerDTO = dozerBeanMapper.map(customer1, CustomerDTO.class);
		CustomerDTO newCustomer = customerService.createCustomer(customerDTO);
		assertThat(newCustomer).isNotNull();
		assertThat(newCustomer.getId()).isNotNull();
		
		newCustomer.setMaritalStatus("Married");
		newCustomer.setCreditRating(75);
		
		Optional<CustomerDTO> actualCustomer = customerService.updateCustomer(newCustomer);
		assertThat(actualCustomer).isNotNull();
		assertThat(actualCustomer.get().getId()).isNotNull();
		assertThat(actualCustomer.get().getMaritalStatus()).isEqualTo(newCustomer.getMaritalStatus());
		assertThat(actualCustomer.get().getCreditRating()).isEqualTo(newCustomer.getCreditRating());
	}
	
	@Test
    @Transactional
	public void assertThatCustomerAddressCanBeUpdated() {
		CityDTO cityDTO = addressService.getByCity("Melbourne").get();
		address1.setCity(dozerBeanMapper.map(cityDTO, City.class));
		List<Address> addresses = new ArrayList<>();
		addresses.add(address1);
		addresses.add(address2);
		customer1.setAddress(addresses);
		CustomerDTO customerDTO = dozerBeanMapper.map(customer1, CustomerDTO.class);
		CustomerDTO newCustomer = customerService.createCustomer(customerDTO);
		assertThat(newCustomer).isNotNull();
		assertThat(newCustomer.getId()).isNotNull();
		
		newCustomer.setMaritalStatus("Married");
		newCustomer.setCreditRating(75);
		AddressDTO newAddress = !newCustomer.getAddress().isEmpty() ? newCustomer.getAddress().get(0) : null;
		if(newAddress != null) {
			newAddress.setPinCode(5001);
			newAddress.setSuburb("Northern Suburb");
		}
		Optional<CustomerDTO> actualCustomer = customerService.updateCustomer(newCustomer);
		assertThat(actualCustomer).isNotNull();
		assertThat(actualCustomer.get().getId()).isNotNull();
		assertThat(actualCustomer.get().getMaritalStatus()).isEqualTo(newCustomer.getMaritalStatus());
		assertThat(actualCustomer.get().getCreditRating()).isEqualTo(newCustomer.getCreditRating());
		assertThat(actualCustomer.get().getAddress().size()).isGreaterThanOrEqualTo(2);
		assertThat(actualCustomer.get().getAddress().get(0).getPinCode()).isEqualTo(5001);
		assertThat(actualCustomer.get().getAddress().get(0).getSuburb()).isEqualTo("Northern Suburb");
		assertThat(actualCustomer.get().getAddress().get(0).equals(actualCustomer.get().getAddress().get(1))).isFalse();
	}
	
	@Test
    @Transactional
	public void assertThatCustomerCanBeDeleted() {
		CustomerDTO customerDTO = dozerBeanMapper.map(customer1, CustomerDTO.class);
		CustomerDTO newCustomer = customerService.createCustomer(customerDTO);
		assertThat(newCustomer).isNotNull();
		assertThat(newCustomer.getId()).isNotNull();
		
		customerService.deleteCustomer(newCustomer.getId());
		
		Optional<CustomerDTO> actualCustomer = customerService.getCustomer(newCustomer.getId());
		assertThat(actualCustomer.isPresent()).isFalse();
	}
	
	@Test
    @Transactional
	public void assertThatAllCustomersCanBeFound() {
		CustomerDTO customerDTO1 = dozerBeanMapper.map(customer1, CustomerDTO.class);
		customerService.createCustomer(customerDTO1);
		CustomerDTO customerDTO2 = dozerBeanMapper.map(customer2, CustomerDTO.class);
		customerService.createCustomer(customerDTO2);
		Page<CustomerDTO> actualCustomers = customerService.getAllCustomers(PageRequest.of(0, 10));
		assertThat(actualCustomers).isNotNull();
		assertThat(actualCustomers.getContent()).hasSize(2);
	}
	
	@Test
    @Transactional
	public void assertThatTwoCustomersNotEqual() {
		CustomerDTO customerDTO1 = dozerBeanMapper.map(customer1, CustomerDTO.class);
		customerService.createCustomer(customerDTO1);
		CustomerDTO customerDTO2 = dozerBeanMapper.map(customer2, CustomerDTO.class);
		customerService.createCustomer(customerDTO2);
		Page<CustomerDTO> actualCustomers = customerService.getAllCustomers(PageRequest.of(0, 10));
		List<CustomerDTO> actualCustomerList = actualCustomers.getContent();
		assertThat(actualCustomerList.size()).isGreaterThanOrEqualTo(2);
		Customer actualCustomer1 = dozerBeanMapper.map(actualCustomerList.get(0), Customer.class);
		Customer actualCustomer2 = dozerBeanMapper.map(actualCustomerList.get(1), Customer.class);
		assertThat(actualCustomer1).isNotNull();
		assertThat(actualCustomer2).isNotNull();
		assertThat(actualCustomer1.equals(actualCustomer2)).isFalse();
	}
	
}
