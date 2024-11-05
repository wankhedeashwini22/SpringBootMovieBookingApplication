package com.ultraPlexMovieBookingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CinemaDto {

        private Long cinemaId;
        private String cinemaName;
        private String location;
        private int totalScreens;
        private List<MovieDto> movies;
        private List<ScreenDto> screens;
    }
