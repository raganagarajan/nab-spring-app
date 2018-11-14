package com.nab.cis.web.rest.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.SpringAdviceTrait;

public class WebExceptionTranslatorTests {
	
	private final SpringAdviceTrait unit = new SpringAdviceTrait() {
    };
    
	@Test
	public void processExceptionTest() {
		WebExceptionTranslator translator = new WebExceptionTranslator();
		MockHttpServletRequest mockRequest = new MockHttpServletRequest();
		NativeWebRequest webRequest = new ServletWebRequest(mockRequest);
		final ResponseEntity<Problem> result = unit.create(new CustomerIdAlreadyFoundException(), webRequest);
		ResponseEntity<Problem> actualResult = translator.process(result, webRequest);
		assertThat(actualResult).isNotNull();
		Problem content = actualResult.getBody();
		assertThat(content.getStatus().getStatusCode()).isEqualTo(Status.BAD_REQUEST.getStatusCode());
		assertThat(content.getType()).isEqualTo(Problem.DEFAULT_TYPE);
		assertThat(content.getTitle()).isEqualTo("Customer Id Already Found");
		
	}
}
