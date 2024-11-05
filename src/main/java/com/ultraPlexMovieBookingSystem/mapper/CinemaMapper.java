package com.ultraPlexMovieBookingSystem.mapper;

import com.ultraPlexMovieBookingSystem.dto.CinemaDto;
import com.ultraPlexMovieBookingSystem.entity.Cinema;

import java.util.stream.Collectors;

public class CinemaMapper {

    public static CinemaDto toCinemaDto(Cinema cinema) {
        return CinemaDto.builder().cinemaId(cinema.getCinemaId()).cinemaName(cinema.getCinemaName())
                .location(cinema.getLocation())
                .totalScreens(cinema.getTotalScreens())
                .screens(cinema.getScreens().stream().map(s -> ScreenMapper.toScreenDto(s)).collect(Collectors.toList()))
                .movies(cinema.getMovies().stream().map(m -> MovieMapper.toMovieDto(m)).collect(Collectors.toList()))
                .build();
    }

    public static Cinema toCinemaEntity(CinemaDto cinemaDto) {
        return Cinema.builder().cinemaId(cinemaDto.getCinemaId()).cinemaName(cinemaDto.getCinemaName())
                .location(cinemaDto.getLocation())
                .totalScreens(cinemaDto.getTotalScreens())
                .build();
    }
}
