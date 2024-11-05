package com.ultraPlexMovieBookingSystem.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class CinemaRequest {

    private Long cinemaId;
    private String cinemaName;
    private String location;
    private int totalScreens;
}
