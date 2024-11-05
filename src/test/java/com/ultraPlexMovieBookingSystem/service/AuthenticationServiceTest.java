package com.ultraPlexMovieBookingSystem.service;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    HttpServletRequest httpServletRequest;
    @InjectMocks
    AuthenticationService authenticationService;

    @Test
    void authenticationServiceTest() {
        when(httpServletRequest.getHeader("X-API-KEY")).thenReturn("movieBookingApiKey");
        Authentication authentication = authenticationService.getAuthentication(httpServletRequest);
        assertNotNull(authentication);
        assertEquals("movieBookingApiKey", authentication.getPrincipal());
        assertEquals(AuthorityUtils.NO_AUTHORITIES, authentication.getAuthorities());
    }
}