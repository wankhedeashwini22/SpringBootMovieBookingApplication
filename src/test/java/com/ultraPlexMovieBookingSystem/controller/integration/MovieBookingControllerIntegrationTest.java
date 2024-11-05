package com.ultraPlexMovieBookingSystem.controller.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieBookingControllerIntegrationTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void givenAuthKeyRequestOnPrivateService_should_unauthorized() throws Exception {
        ResponseEntity<String> result = template
                .getForEntity("/ultraplex/movie/", String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }


}