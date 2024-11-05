package com.ultraPlexMovieBookingSystem.controller;

import com.ultraPlexMovieBookingSystem.builder.CinemaDtoBuilder;
import com.ultraPlexMovieBookingSystem.builder.ScreenDtoBuilder;
import com.ultraPlexMovieBookingSystem.constants.BookingConstants;
import com.ultraPlexMovieBookingSystem.dto.CinemaDto;
import com.ultraPlexMovieBookingSystem.dto.MovieDto;
import com.ultraPlexMovieBookingSystem.dto.ResponseDto;
import com.ultraPlexMovieBookingSystem.dto.SeatDto;
import com.ultraPlexMovieBookingSystem.dto.ShowtimeDto;
import com.ultraPlexMovieBookingSystem.entity.Cinema;
import com.ultraPlexMovieBookingSystem.entity.Movie;
import com.ultraPlexMovieBookingSystem.mapper.CinemaMapper;
import com.ultraPlexMovieBookingSystem.mapper.MovieMapper;
import com.ultraPlexMovieBookingSystem.request.CinemaRequest;
import com.ultraPlexMovieBookingSystem.request.ScreenRequest;
import com.ultraPlexMovieBookingSystem.service.MovieManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.ultraPlexMovieBookingSystem.constants.BookingConstants.CINEMA_ID;
import static com.ultraPlexMovieBookingSystem.constants.BookingConstants.MOVIE_ID;
import static com.ultraPlexMovieBookingSystem.constants.BookingConstants.SCREEN_ID;
import static com.ultraPlexMovieBookingSystem.constants.BookingConstants.SEAT_ID;
import static com.ultraPlexMovieBookingSystem.constants.BookingConstants.SHOWTIME_ID;

@RestController
@RequestMapping("/ultraplex/movie/manage")
public class MovieManageController {

    @Autowired
    MovieManageService movieManageService;


    @PostMapping(value = "/addCinema", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> addCinema(@RequestHeader(value = "X-API-KEY") String apiKey, @RequestBody CinemaRequest cinemaRequest) {
        Long cinemaId = movieManageService.addCinema(CinemaDtoBuilder.buildCinemaDto(cinemaRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(BookingConstants.STATUS_201,
                BookingConstants.CINEMA_STATUS_MESSAGE_201, CINEMA_ID+cinemaId));
    }

    @PostMapping(value = "/addScreensToCinema" , produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<ResponseDto> addScreensToCinema(@RequestHeader(value = "X-API-KEY") String apiKey, @RequestBody ScreenRequest screenRequest) {
        Long screenId = movieManageService.addScreensToCinema(ScreenDtoBuilder.buildScreenDto(screenRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(BookingConstants.STATUS_201,
                BookingConstants.SCREEN_STATUS_MESSAGE_201, SCREEN_ID+screenId));
    }

    @PostMapping(value = "/addMovie", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> addMovie(@RequestHeader(value = "X-API-KEY") String apiKey, @RequestBody MovieDto movieDto) {
        Long movieId = movieManageService.addMovies(movieDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(BookingConstants.STATUS_201,
                BookingConstants.MOVIE_STATUS_MESSAGE_201, MOVIE_ID+movieId));
    }

    @PostMapping(value = "/addScreensForMovies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> addScreensForMovies(@RequestHeader(value = "X-API-KEY") String apiKey, @RequestBody ScreenRequest screenRequest) {
        Long screenId = movieManageService.addScreensForMovies(ScreenDtoBuilder.buildScreenDto(screenRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(BookingConstants.STATUS_201,
                BookingConstants.MOVIE_SCREENS_STATUS_MESSAGE_201, SCREEN_ID+screenId));
    }

    @PostMapping(value = "/addSeatsForMovies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> addSeatsForMovies(@RequestHeader(value = "X-API-KEY") String apiKey, @RequestBody SeatDto seatDto) {
        Long seatId = movieManageService.addSeatsForMovies(seatDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(BookingConstants.STATUS_201,
                BookingConstants.MOVIE_SEATS_STATUS_MESSAGE_201, SEAT_ID+seatId));
    }

    @PostMapping(value = "/addShowTimesForMovies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> addShowTimesForMovies(@RequestHeader(value = "X-API-KEY") String apiKey, @RequestBody ShowtimeDto showtimeDto) {
        Long   showtimeId = movieManageService.addShowTimesForMovies(showtimeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(BookingConstants.STATUS_201,
                BookingConstants.SHOWTIME_STATUS_MESSAGE_201, SHOWTIME_ID+showtimeId));
    }

    @GetMapping(value = "/getAllScreeningsForACinemaForASpecificDay", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CinemaDto> getAllScreeningsForACinemaForASpecificDay(@RequestHeader(value = "X-API-KEY") String apiKey, @RequestParam(value = "cinemaId") Long cinemaId, @RequestParam (value = "date") String date) {
        Optional<Cinema> cinema = movieManageService.getAllScreeningsForACinemaForASpecificDay(cinemaId, date);
        return ResponseEntity.status(HttpStatus.OK).body(CinemaMapper.toCinemaDto(cinema.orElse(null)));

    }

    @GetMapping(value = "/getAllScreeningsOfASpecificMovieForASpecificDay", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDto> getAllScreeningsOfASpecificMovieForASpecificDay(@RequestHeader(value = "X-API-KEY") String apiKey, @RequestParam(value = "movieId") Long movieId, @RequestParam(value = "date") String day) {
        Optional<Movie> movie = movieManageService.getAllScreeningsOfASpecificMovieForASpecificDay(movieId, day);
        return ResponseEntity.status(HttpStatus.OK).body(MovieMapper.toMovieDto(movie.orElse(null)));
    }
}
