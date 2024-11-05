package com.ultraPlexMovieBookingSystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@Valid
public class PaymentDto {

    @Pattern(regexp = "^[1-9][0-9]*$", message = "paymentId must be a positive integer")
    private Long paymentId;
    @Pattern(regexp = "^[1-9][0-9]*$", message = "bookingId must be a positive integer")
    private Long bookingId;
    @Pattern(regexp = "^[1-9][0-9]*$", message = "amount must be a positive double")
    private double amount;
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private LocalDateTime paymentDate;
    private String paymentMethod;
    @JsonIgnore
    private String paymentStatus;
}
