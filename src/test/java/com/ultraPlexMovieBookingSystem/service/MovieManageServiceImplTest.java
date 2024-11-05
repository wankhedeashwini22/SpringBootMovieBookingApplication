package com.ultraPlexMovieBookingSystem.service;

import com.ultraPlexMovieBookingSystem.dto.CinemaDto;
import com.ultraPlexMovieBookingSystem.dto.MovieDto;
import com.ultraPlexMovieBookingSystem.dto.ScreenDto;
import com.ultraPlexMovieBookingSystem.dto.SeatDto;
import com.ultraPlexMovieBookingSystem.dto.ShowtimeDto;
import com.ultraPlexMovieBookingSystem.entity.Cinema;
import com.ultraPlexMovieBookingSystem.entity.Movie;
import com.ultraPlexMovieBookingSystem.entity.Screen;
import com.ultraPlexMovieBookingSystem.entity.Seat;
import com.ultraPlexMovieBookingSystem.entity.Showtime;
import com.ultraPlexMovieBookingSystem.repository.CinemaRepository;
import com.ultraPlexMovieBookingSystem.repository.MovieRepository;
import com.ultraPlexMovieBookingSystem.repository.ScreenRepository;
import com.ultraPlexMovieBookingSystem.repository.SeatRepository;
import com.ultraPlexMovieBookingSystem.repository.ShowtimeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieManageServiceImplTest {

    @Mock
    CinemaRepository cinemaRepository;

    @Mock
    ScreenRepository screenRepository;

    @Mock
    MovieRepository movieRepository;

    @Mock
    SeatRepository seatRepository;

    @Mock
    ShowtimeRepository showtimeRepository;

    @InjectMocks
    private MovieManageServiceImpl movieManageServiceImplTest;

    @Test
    void addCinema_Success() {
        when(cinemaRepository.save(any(Cinema.class))).thenReturn(Cinema.builder().cinemaId(18L).cinemaName("cinema").location("location").totalScreens(1).build());
        Long cinemaId = movieManageServiceImplTest.addCinema(CinemaDto.builder().cinemaId(18L).cinemaName("cinema").location("location").totalScreens(1).build());
        assertNotNull(cinemaId);
        assertEquals(18L, cinemaId);
    }

    @Test
    void addCinema_Error_ClassCastException() {
        when(cinemaRepository.save(any())).thenReturn(CinemaDto.builder().cinemaId(18L).cinemaName("cinema").location("location").totalScreens(1).build());
        assertThrows(ClassCastException.class, () -> movieManageServiceImplTest.addCinema(CinemaDto.builder().cinemaId(18L).cinemaName("cinema").location("location").totalScreens(1).build()));
    }

    @Test
    void addScreensToCinema_Success() {
        when(cinemaRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(Cinema.builder().cinemaId(18L).cinemaName("cinema").location("location").totalScreens(1).build()));
        when(screenRepository.save(any(Screen.class))).thenReturn(Screen.builder().screenId(1L).build());
        Long screenId = movieManageServiceImplTest.addScreensToCinema(ScreenDto.builder().cinemaId(18L).screenId(15L).build());
        assertNotNull(screenId);
        assertEquals(15L, screenId);
        verify(cinemaRepository, times(1)).findById(anyLong());
        verifyNoInteractions(movieRepository);

    }

    @Test
    void addMovies_Success() {
        when(movieRepository.save(any(Movie.class))).thenReturn(Movie.builder().movieId(145L).title("title").duration(120).build());
        Long movieId = movieManageServiceImplTest.addMovies(MovieDto.builder().movieId(145L).title("title").duration(120).build());
        assertNotNull(movieId);
        assertEquals(145L, movieId);
        verify(movieRepository, times(1)).save(any());
        verifyNoInteractions (seatRepository, showtimeRepository);
    }

    @Test
    void addMovies_Error_ClassCastException() {
        when(movieRepository.save(any())).thenReturn(MovieDto.builder().movieId(145L).title("title").duration(120).build());
        assertThrows(ClassCastException.class, () -> movieManageServiceImplTest.addMovies(MovieDto.builder().movieId(145L).title("title").duration(120).build()));
        verify(movieRepository, times(1)).save(any());
        verifyNoInteractions (seatRepository, showtimeRepository);
    }

    @Test
    void addScreensForMovies_Success() {
        when(movieRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(Movie.builder().movieId(145L).title("title").duration(120).build()));
        when(screenRepository.save(any(Screen.class))).thenReturn(Screen.builder().screenId(1L).build());
        Long screenId = movieManageServiceImplTest.addScreensForMovies(ScreenDto.builder().movieId(145L).screenId(15L).build());
        assertNotNull(screenId);
        assertEquals(15L, screenId);
        verify(movieRepository, times(1)).findById(anyLong());
        verifyNoInteractions(seatRepository, showtimeRepository);
    }

    @Test
    void addSeatsForMovies() {

        ArrayList<Seat> seats = new ArrayList<>();
        seats.add(Seat.builder().seatId(189L).build());

        when(screenRepository.findById(SeatDto.builder().seatId(189L).build().getScreenId()))
                .thenReturn(Optional.ofNullable(Screen.builder().screenId(17L).movie(Movie.builder().movieId(1L).build()).seats(seats).build()));
        Long seatId = movieManageServiceImplTest.addSeatsForMovies(SeatDto.builder().seatId(189L).build());
        assertNotNull(seatId);
        assertEquals(189L, seatId);
        verify(screenRepository, times(1)).findById(SeatDto.builder().seatId(189L).build().getScreenId());
    }

    @Test
    void addShowTimesForMovies_Success() {

        when(showtimeRepository.save(any(Showtime.class))).thenReturn(Showtime.builder().showtimeId(31L).build());

        Long showtimeId = movieManageServiceImplTest.addShowTimesForMovies(ShowtimeDto.builder().showtimeId(31L).build());

        assertNotNull(showtimeId);
        assertEquals(31L, showtimeId);
        verify(showtimeRepository, times(1)).save(any(Showtime.class));
    }

    @Test
    void addShowTimesForMovies_Error_NullPointerException() {

        assertThrows(NullPointerException.class, () -> movieManageServiceImplTest.addShowTimesForMovies(null));

        verifyNoInteractions(showtimeRepository);
    }

    @Test
    void getAllScreeningsForACinemaForASpecificDay_Success() {
        when(cinemaRepository.findById(65L)).thenReturn(Optional.ofNullable(Cinema.builder().cinemaId(65L).cinemaName("cinema").location("location").totalScreens(1).build()));
        Optional<Cinema> cinema = movieManageServiceImplTest.getAllScreeningsForACinemaForASpecificDay(65L, "Monday");
        assertTrue(cinema.isPresent());
        assertNotNull(cinema.get());
        assertEquals(65L, cinema.get().getCinemaId());
        verify(cinemaRepository, times(1)).findById(65L);
    }

    @Test
    void getAllScreeningsOfASpecificMovieForASpecificDay_Success() {
        when(movieRepository.findById(65L)).thenReturn(Optional.ofNullable(Movie.builder().movieId(65L).title("title").duration(120).rating("9").build()));
        Optional<Movie> movie = movieManageServiceImplTest.getAllScreeningsOfASpecificMovieForASpecificDay(65L, "Monday");
        assertTrue(movie.isPresent());
        assertNotNull(movie.get());
        assertEquals(65L, movie.get().getMovieId());
        assertEquals("9", movie.get().getRating());
        verify(movieRepository, times(1)).findById(65L);
    }

}