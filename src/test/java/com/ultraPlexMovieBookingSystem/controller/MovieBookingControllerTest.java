package com.ultraPlexMovieBookingSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ultraPlexMovieBookingSystem.dto.BookingDto;
import com.ultraPlexMovieBookingSystem.service.MoovieBookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieBookingController.class)
class MovieBookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MoovieBookingService movieBookingServiceMock;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void bookingsForMovies_test_Forbidden() throws Exception {

        mockMvc.perform(post("/ultraplex/movie/booking/bookingsForMovies")
                        .header("X-API-KEY","wrongApiKey")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(BookingDto.builder().bookingId(1L).customerId(1L)
                                .showtimeId(1L).seatId(1L).totalAmount(20).bookingTime(LocalDateTime.now()).build()))
                )
                .andExpect(status().isForbidden())
                .andReturn().getResponse();
        verify(movieBookingServiceMock, never()).bookingsForMovies(any());

    }

}