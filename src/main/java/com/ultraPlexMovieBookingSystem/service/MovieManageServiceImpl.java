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
import com.ultraPlexMovieBookingSystem.mapper.CinemaMapper;
import com.ultraPlexMovieBookingSystem.mapper.MovieMapper;
import com.ultraPlexMovieBookingSystem.mapper.ScreenMapper;
import com.ultraPlexMovieBookingSystem.mapper.SeatMapper;
import com.ultraPlexMovieBookingSystem.mapper.ShowtimeMapper;
import com.ultraPlexMovieBookingSystem.repository.CinemaRepository;
import com.ultraPlexMovieBookingSystem.repository.MovieRepository;
import com.ultraPlexMovieBookingSystem.repository.ScreenRepository;
import com.ultraPlexMovieBookingSystem.repository.SeatRepository;
import com.ultraPlexMovieBookingSystem.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieManageServiceImpl implements MovieManageService {

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    ShowtimeRepository showtimeRepository;


    @Override
    public Long addCinema(CinemaDto cinemaDto) {
        Cinema cinema = CinemaMapper.toCinemaEntity(cinemaDto);
        cinemaRepository.save(cinema);
        return cinema.getCinemaId();
    }

    @Override
    public Long addScreensToCinema(ScreenDto screenDto) {
        Optional<Cinema> cinema = cinemaRepository.findById(screenDto.getCinemaId());
        Screen screen = ScreenMapper.toScreenEntity(screenDto, cinema.isPresent() ? cinema.get() : null, null);
        screenRepository.save(screen);
        return screen.getScreenId();
    }

    @Override
    public Long addMovies(MovieDto movieDto) {
        Movie movie = MovieMapper.toMovieEntity(movieDto);
        movieRepository.save(movie);
        return movie.getMovieId();
    }

    @Override
    public Long addScreensForMovies(ScreenDto screenDto) {

        Optional<Cinema> cinema = cinemaRepository.findById(screenDto.getCinemaId());

        Optional<Movie> movie = movieRepository.findById(screenDto.getMovieId());

        Screen screen = ScreenMapper.toScreenEntity(screenDto, cinema.isPresent() ? cinema.get() : null, movie.isPresent() ? movie.get() : null);
        screenRepository.save(screen);
        return screen.getScreenId();
    }

    @Override
    public Long addSeatsForMovies(SeatDto seatDto) {

        Optional<Screen> screen = screenRepository.findById(seatDto.getScreenId());

        Seat seat = SeatMapper.toSeatEntity(seatDto, screen.isPresent() ? screen.get() : null);

        seatRepository.save(seat);
        return seat.getSeatId();
    }

    @Override
    public Long addShowTimesForMovies(ShowtimeDto showtimeDto) {

        Optional<Movie> movie = movieRepository.findById(showtimeDto.getMovieId());
        Optional<Screen> screen = screenRepository.findById(showtimeDto.getScreenId());

        Showtime showtime = ShowtimeMapper.toShowtimeEntity(showtimeDto, movie.isPresent() ? movie.get() : null, screen.isPresent() ? screen.get() : null);
        showtimeRepository.save(showtime);
        return showtime.getShowtimeId();
    }

    @Override
    public Optional<Cinema> getAllScreeningsForACinemaForASpecificDay(Long cinemaId, String day) {
        return cinemaRepository.findById(cinemaId);

    }

    @Override
    public Optional<Movie> getAllScreeningsOfASpecificMovieForASpecificDay(Long movieId, String day) {
        return movieRepository.findById(movieId);

    }
}
