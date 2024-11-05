package com.ultraPlexMovieBookingSystem.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ultraPlexMovieBookingSystem.entity.Cinema;
import com.ultraPlexMovieBookingSystem.service.MovieManageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieManageController.class)
class MovieManageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MovieManageService movieManageServiceMock;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void bookingsForMovies_test_Forbidden() throws Exception {

        mockMvc.perform(post("/ultraplex/movie/manage/addCinema")
                        .header("X-API-KEY","wrongApiKey")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(Cinema.builder().cinemaId(1L)
                                .cinemaName("cinema").location("location")
                                .totalScreens(1).build())))//.cinemaId(1L).cinemaName("cinema").location("location").totalScreens(1).build()))
                .andExpect(status().isForbidden())
                .andReturn().getResponse();
        verify(movieManageServiceMock, never()).addCinema(any());

    }

}