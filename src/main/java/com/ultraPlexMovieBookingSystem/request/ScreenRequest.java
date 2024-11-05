package com.ultraPlexMovieBookingSystem.request;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Valid
public class ScreenRequest {
    private Long screenId;
    private String screenNumber;
    private Long cinemaId;
    private Long movieId;
}
