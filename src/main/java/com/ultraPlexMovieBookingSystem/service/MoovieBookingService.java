package com.ultraPlexMovieBookingSystem.service;

import com.ultraPlexMovieBookingSystem.dto.BookingDto;
import com.ultraPlexMovieBookingSystem.dto.CustomerDto;
import com.ultraPlexMovieBookingSystem.dto.PaymentDto;

public interface MoovieBookingService {

    Long registerCustomer(CustomerDto customerDto);

    Long bookingsForMovies(BookingDto bookingDto);

    Long cancelBooking(Long bookingId);

    String makePayment(PaymentDto paymentDto);

    CustomerDto getCustomerDetails(Long customerId);
}
