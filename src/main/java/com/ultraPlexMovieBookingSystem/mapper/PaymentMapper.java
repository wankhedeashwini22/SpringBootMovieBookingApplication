package com.ultraPlexMovieBookingSystem.mapper;

import com.ultraPlexMovieBookingSystem.dto.PaymentDto;
import com.ultraPlexMovieBookingSystem.entity.Booking;
import com.ultraPlexMovieBookingSystem.entity.Payment;

import java.util.Optional;

public class PaymentMapper {

    public static PaymentDto toPaymentDto(Payment payment) {
        return PaymentDto.builder().paymentId(payment.getPaymentId()).bookingId(payment.getBooking().getBookingId())
                .paymentDate(payment.getPaymentDate()).paymentMethod(payment.getPaymentMethod())
                .paymentMethod(payment.getPaymentStatus())
                .amount(payment.getAmount())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }

    public static Payment toPaymentEntity(Optional<Booking> booking, PaymentDto dto) {
        return Payment.builder().paymentId(dto.getPaymentId())
                .booking(booking.isPresent() ? booking.get() : null)
                .amount(dto.getAmount())
                .paymentDate(dto.getPaymentDate())
                .paymentMethod(dto.getPaymentMethod())
                .paymentStatus(dto.getPaymentStatus())
                .build();
    }
}
