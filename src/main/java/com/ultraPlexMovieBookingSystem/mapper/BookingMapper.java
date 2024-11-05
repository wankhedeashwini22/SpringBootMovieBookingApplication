package com.ultraPlexMovieBookingSystem.mapper;

import com.ultraPlexMovieBookingSystem.dto.BookingDto;
import com.ultraPlexMovieBookingSystem.entity.Booking;
import com.ultraPlexMovieBookingSystem.entity.Customer;
import com.ultraPlexMovieBookingSystem.entity.Seat;
import com.ultraPlexMovieBookingSystem.entity.Showtime;

public class BookingMapper {

    public static BookingDto toBookingDto(Booking booking) {
        return BookingDto.builder().bookingId(booking.getBookingId())
                .customerId(booking.getCustomer().getCustomerId())
                .showtimeId(booking.getShowtime().getShowtimeId())
                .totalAmount(booking.getTotalAmount())
                .bookingTime(booking.getBookingTime())
                .build();

    }

    public static Booking toBookingEntity(BookingDto bookingDto, Customer customer, Showtime showtime, Seat seat) {

        return Booking.builder()
                .bookingId(bookingDto.getBookingId())
                .customer(customer)
                .showtime(showtime)
                .totalAmount(bookingDto.getTotalAmount())
                .bookingTime(bookingDto.getBookingTime())
                .build();

    }
}
