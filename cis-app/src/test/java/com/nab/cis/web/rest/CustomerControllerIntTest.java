package com.nab.cis.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.nab.cis.domain.Customer;
import com.nab.cis.repository.CustomerRepository;
import com.nab.cis.service.CustomerService;
import com.nab.cis.service.dto.CustomerDTO;
import com.nab.cis.web.rest.exception.WebExceptionTranslator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CISApplication.class)
public class CustomerControllerIntTest {
	
	 @Autowired
	 private CustomerService customerService;
	 
	 @Autowired
	 private CustomerRepository customerRepository;
	 
	 @Autowired
	 private MappingJackson2HttpMessageConverter jacksonMessageConverter;
	 
	 @Autowired
	 private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
	 
	 @Autowired
	 private WebExceptionTranslator webExceptionTranslator;
	 
	 @Autowired
	 private DozerBeanMapper dozerBeanMapper;
	 
	 private MockMvc restCustomerMockMvc;
	 
	 private static final String DEFAULT_FIRSTNAME = "john";
	 private static final String DEFAULT_MIDDLENAME = "Van";
	 private static final String DEFAULT_SURNAME = "Damme";
	 private static final String DEFAULT_INITIAL = "JVD";
	 private static final String DEFAULT_TITLE = "Mr";
	 private static final String DEFAULT_GENDER = "Male";
	 private static final String DEFAULT_MARITAL_STATUS = "NeverMarried";
	 private static final Integer DEFAULT_CREDIT_RATING = 55;
	 private static final Boolean DEFAULT_NAB_CUSTOMER = true;
	 
	 private static final String UPDATE_MARITAL_STATUS = "Married";
	 private static final Integer UPDATE_CREDIT_RATING = 80;
	 
	 @Before
	 public void setup() {
		 CustomerController controller = new CustomerController(customerService);
		 this.restCustomerMockMvc = MockMvcBuilders.standaloneSetup(controller)
	            .setCustomArgumentResolvers(pageableArgumentResolver)
	            .setControllerAdvice(webExceptionTranslator)
	            .setMessageConverters(jacksonMessageConverter)
	            .build();
	 }
	 
	 public static CustomerDTO createCustomerObj() {
		 CustomerDTO customer = new CustomerDTO();
		 customer.setId(1L);
		 customer.setFirstName(DEFAULT_FIRSTNAME);
		 customer.setMiddleName(DEFAULT_MIDDLENAME);
		 customer.setSurname(DEFAULT_SURNAME);
		 customer.setInitial(DEFAULT_INITIAL);
		 customer.setTitle(DEFAULT_TITLE);
		 customer.setGender(DEFAULT_GENDER);
		 customer.setMaritalStatus(DEFAULT_MARITAL_STATUS);
		 customer.setCreditRating(DEFAULT_CREDIT_RATING);
		 customer.setNabCustomer(DEFAULT_NAB_CUSTOMER);
		 return customer;
	 }
	 
	 @Test
	 @Transactional
	 public void createCustomerWithExistingId() throws Exception {
		 int countBeforePost = customerRepository.findAll().size();
		 CustomerDTO customer = createCustomerObj();
		 // Customer with an existing ID cannot be created, this call must fail
		 restCustomerMockMvc.perform(post("/api/customers")
				 .contentType(WebTestUtil.APPLICATION_JSON_UTF8)
				 .content(WebTestUtil.convertObjectToJsonBytes(customer)))
            	 .andExpect(status().isBadRequest());
		 // Validate the Customer in the database
		 List<Customer> userList = customerRepository.findAll();
		 assertThat(userList).hasSize(countBeforePost);
	 }
	 
	 @Test
	 @Transactional
	 public void createCustomer() throws Exception {
		 int countBeforePost = customerRepository.findAll().size();
		 CustomerDTO customer = createCustomerObj();
		 customer.setId(null);
		 restCustomerMockMvc.perform(post("/api/customers")
				 .contentType(WebTestUtil.APPLICATION_JSON_UTF8)
		         .content(WebTestUtil.convertObjectToJsonBytes(customer)))
		         .andExpect(status().isCreated());

		 // Validate the Customer in the database
		 List<Customer> userList = customerRepository.findAll();
		 assertThat(userList).hasSize(countBeforePost + 1);
	 }
	 
	 @Test
	 @Transactional
	 public void getAllCustomers() throws Exception {
		 CustomerDTO customer = createCustomerObj();
		 customer.setId(null);
		 customerRepository.saveAndFlush(dozerBeanMapper.map(customer, Customer.class));
		 // Get all the customers
		 restCustomerMockMvc.perform(get("/api/customers")
				.accept(MediaType.APPLICATION_JSON))
            	.andExpect(status().isOk())
            	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            	.andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            	.andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRSTNAME)))
            	.andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME)))
            	.andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLENAME)))
            	.andExpect(jsonPath("$.[*].creditRating").value(hasItem(DEFAULT_CREDIT_RATING)))
            	.andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)));
	 }
	 
	 @Test
	 @Transactional
	 public void updateUser() throws Exception {
		 CustomerDTO customer = createCustomerObj();
		 customer.setId(null);
		 Customer newCustomer = dozerBeanMapper.map(customer, Customer.class);
		 customerRepository.saveAndFlush(newCustomer);
		 
		 // Update the Customer
		 Customer updateCustomer = customerRepository.findById(newCustomer.getId()).get();
		 updateCustomer.setCreditRating(UPDATE_CREDIT_RATING);
		 updateCustomer.setMaritalStatus(UPDATE_MARITAL_STATUS);
	    
		 restCustomerMockMvc.perform(put("/api/customers")
				 .contentType(WebTestUtil.APPLICATION_JSON_UTF8)
		         .content(WebTestUtil.convertObjectToJsonBytes(updateCustomer)))
		         .andExpect(status().isOk());
		 
		 List<Customer> customerList = customerRepository.findAll();
		 Customer testCustomer = customerList.get(customerList.size() - 1);
		 assertThat(testCustomer.getCreditRating()).isEqualTo(UPDATE_CREDIT_RATING);
		 assertThat(testCustomer.getMaritalStatus()).isEqualTo(UPDATE_MARITAL_STATUS);

	 }
	 
	 @Test
	 @Transactional
	 public void deleteUser() throws Exception {
		 CustomerDTO customer = createCustomerObj();
		 customer.setId(null);
		 Customer newCustomer = dozerBeanMapper.map(customer, Customer.class);
		 customerRepository.saveAndFlush(newCustomer);
		 int sizeBeforeDelete = customerRepository.findAll().size();

		 // Delete the Customer
		 restCustomerMockMvc.perform(delete("/api/customers/{id}", newCustomer.getId())
				.accept(WebTestUtil.APPLICATION_JSON_UTF8))
         		.andExpect(status().isOk());
		 
		 // Validate db size
		 List<Customer> customerList = customerRepository.findAll();
		 assertThat(customerList).hasSize(sizeBeforeDelete - 1);
    }

}
