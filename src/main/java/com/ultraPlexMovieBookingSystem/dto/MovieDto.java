package com.ultraPlexMovieBookingSystem.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@Valid
public class MovieDto {

    private Long movieId;
    private String title;
    private int duration;
    private String rating;
    private Date releaseDate;
}
