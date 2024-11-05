package com.ultraPlexMovieBookingSystem.mapper;

import com.ultraPlexMovieBookingSystem.dto.ScreenDto;
import com.ultraPlexMovieBookingSystem.entity.Cinema;
import com.ultraPlexMovieBookingSystem.entity.Movie;
import com.ultraPlexMovieBookingSystem.entity.Screen;

public class ScreenMapper {

    public static ScreenDto toScreenDto(Screen screen) {
        return ScreenDto.builder()
                .screenId(screen.getScreenId()).screenNumber(screen.getScreenNumber())
                .cinemaId(screen.getCinema().getCinemaId())
                .showtimes(screen.getShowtimes().stream().map(ShowtimeMapper::toShowtimeDto).toList())
                .seats(screen.getSeats().stream().map(SeatMapper::toSeatDto).toList())
                .build();
    }

    public static Screen toScreenEntity(ScreenDto dto, Cinema cinema, Movie movie) {
        return Screen.builder().screenId(dto.getScreenId())
                .screenNumber(dto.getScreenNumber())
                .cinema(cinema)
                .movie(movie)
                .build();
    }
}
