package com.nab.cis.web.rest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class WebTestUtil {
	
	 public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
	            MediaType.APPLICATION_JSON.getType(),
	            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
	 
	 public static byte[] convertObjectToJsonBytes(Object object)
	            throws IOException {
		 ObjectMapper mapper = new ObjectMapper();
		 mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

		 JavaTimeModule module = new JavaTimeModule();
		 mapper.registerModule(module);

		 return mapper.writeValueAsBytes(object);
	 }
	 
	 public static byte[] createByteArray(int size, String data) {
		 byte[] byteArray = new byte[size];
		 for (int i = 0; i < size; i++) {
			 byteArray[i] = Byte.parseByte(data, 2);
		 }
		 return byteArray;
	 }
}
