package com.ultraPlexMovieBookingSystem.builder;

import com.ultraPlexMovieBookingSystem.dto.CinemaDto;
import com.ultraPlexMovieBookingSystem.request.CinemaRequest;

public class CinemaDtoBuilder {

    public static CinemaDto buildCinemaDto(CinemaRequest cinemaRequest) {
        return CinemaDto.builder().cinemaId(cinemaRequest.getCinemaId())
                .cinemaName(cinemaRequest.getCinemaName())
                .location(cinemaRequest.getLocation())
                .totalScreens(cinemaRequest.getTotalScreens())
                .build();
    }
}
