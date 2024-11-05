package com.ultraPlexMovieBookingSystem.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Valid
public class ScreenDto {
    private Long screenId;
    private String screenNumber;
    private Long cinemaId;
    private Long movieId;
    private List<SeatDto> seats;
    private List<ShowtimeDto> showtimes;
}
