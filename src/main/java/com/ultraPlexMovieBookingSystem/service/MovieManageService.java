package com.ultraPlexMovieBookingSystem.service;

import com.ultraPlexMovieBookingSystem.dto.CinemaDto;
import com.ultraPlexMovieBookingSystem.dto.MovieDto;
import com.ultraPlexMovieBookingSystem.dto.ScreenDto;
import com.ultraPlexMovieBookingSystem.dto.SeatDto;
import com.ultraPlexMovieBookingSystem.dto.ShowtimeDto;
import com.ultraPlexMovieBookingSystem.entity.Cinema;
import com.ultraPlexMovieBookingSystem.entity.Movie;

import java.util.Optional;

public interface MovieManageService {
    Long addCinema(CinemaDto cinemaDto);
    Long addScreensToCinema(ScreenDto screenDto);
    Long addMovies(MovieDto movieDto);
    Long addScreensForMovies(ScreenDto screenDto);
    Long addSeatsForMovies(SeatDto seatDto);
    Long addShowTimesForMovies(ShowtimeDto showtimeDto);
    Optional<Cinema> getAllScreeningsForACinemaForASpecificDay(Long CinemaId, String day);
    Optional<Movie> getAllScreeningsOfASpecificMovieForASpecificDay(Long movieId, String day);

}
