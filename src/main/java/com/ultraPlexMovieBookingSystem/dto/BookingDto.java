package com.ultraPlexMovieBookingSystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class BookingDto {
    private Long bookingId;
    @Pattern(regexp = "^[1-9][0-9]*$", message = "customerId must be a positive integer")
    private Long customerId;
    @Pattern(regexp = "^[1-9][0-9]*$", message = "showtimeId must be a positive integer")
    private Long showtimeId;
    @Pattern(regexp = "^[1-9][0-9]*$", message = "seatId must be a positive integer")
    private Long seatId;
    @Pattern(regexp = "^[1-9][0-9]*$", message = "paymentId must be a positive integer")
    private double totalAmount;
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")
    private LocalDateTime bookingTime;
}
