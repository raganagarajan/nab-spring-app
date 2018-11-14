package com.nab.cis.web.rest.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;

public class PaginationUtilTest {

	@Test
    public void generatePaginationHttpHeadersTest() {
        String baseUrl = "/api/customers";
        List<String> content = new ArrayList<>();
        Page<String> page = new PageImpl<>(content, PageRequest.of(6, 50), 400L);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, baseUrl);
        List<String> strHeaders = headers.get(HttpHeaders.LINK);
        assertNotNull(strHeaders);
        assertTrue(strHeaders.size() == 1);
        String headerData = strHeaders.get(0);
        assertTrue(headerData.split(",").length == 4);
        String expectedData = "</api/customers?page=7&size=50>; rel=\"next\","
                + "</api/customers?page=5&size=50>; rel=\"prev\","
                + "</api/customers?page=7&size=50>; rel=\"last\","
                + "</api/customers?page=0&size=50>; rel=\"first\"";
        assertEquals(expectedData, headerData);
        List<String> xTotalCountHeaders = headers.get("X-Total-Count");
        assertTrue(xTotalCountHeaders.size() == 1);
        assertTrue(Long.valueOf(xTotalCountHeaders.get(0)).equals(400L));
    }

    @Test
    public void commaTest() {
        String baseUrl = "/api/customers";
        List<String> content = new ArrayList<>();
        Page<String> page = new PageImpl<>(content);
        String query = "Cust1, Cust2";
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, baseUrl);
        List<String> strHeaders = headers.get(HttpHeaders.LINK);
        assertNotNull(strHeaders);
        assertTrue(strHeaders.size() == 1);
        String headerData = strHeaders.get(0);
        assertTrue(headerData.split(",").length == 2);
        String expectedData = "</api/customers?page=0&size=0&query=Cust1%2C+Cust2>; rel=\"last\","
                + "</api/customers?page=0&size=0&query=Cust1%2C+Cust2>; rel=\"first\"";
        assertEquals(expectedData, headerData);
        List<String> xTotalCountHeaders = headers.get("X-Total-Count");
        assertTrue(xTotalCountHeaders.size() == 1);
        assertTrue(Long.valueOf(xTotalCountHeaders.get(0)).equals(0L));
    }
}
