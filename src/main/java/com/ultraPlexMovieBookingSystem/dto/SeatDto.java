package com.ultraPlexMovieBookingSystem.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Valid
public class SeatDto {
    private Long seatId;
    @Pattern(regexp = "^[A-Z][1-9][0-9]?$", message = "Invalid seat number")
    private String seatNumber;
    private boolean isAvailable;
    private Long screenId;

}
