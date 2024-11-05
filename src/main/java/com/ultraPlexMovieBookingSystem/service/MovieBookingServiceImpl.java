package com.ultraPlexMovieBookingSystem.service;

import com.ultraPlexMovieBookingSystem.dto.BookingDto;
import com.ultraPlexMovieBookingSystem.dto.CustomerDto;
import com.ultraPlexMovieBookingSystem.dto.PaymentDto;
import com.ultraPlexMovieBookingSystem.entity.Booking;
import com.ultraPlexMovieBookingSystem.entity.Customer;
import com.ultraPlexMovieBookingSystem.entity.Payment;
import com.ultraPlexMovieBookingSystem.entity.Seat;
import com.ultraPlexMovieBookingSystem.entity.Showtime;
import com.ultraPlexMovieBookingSystem.mapper.BookingMapper;
import com.ultraPlexMovieBookingSystem.mapper.CustomerMapper;
import com.ultraPlexMovieBookingSystem.mapper.PaymentMapper;
import com.ultraPlexMovieBookingSystem.repository.BookingRepository;
import com.ultraPlexMovieBookingSystem.repository.CustomerRepository;
import com.ultraPlexMovieBookingSystem.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class MovieBookingServiceImpl implements MoovieBookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    com.ultraPlexMovieBookingSystem.repository.ShowtimeRepository ShowtimeRepository;

    @Autowired
    com.ultraPlexMovieBookingSystem.repository.SeatRepository SeatRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Long registerCustomer(CustomerDto customerDto) {

        Customer customer = CustomerMapper.toCustomerEntity(customerDto);
        customerRepository.save(customer);
        return customer.getCustomerId();
    }

    @Override
    public Long bookingsForMovies(BookingDto bookingDto) {

        Optional<Customer> customer = customerRepository.findById(bookingDto.getCustomerId());

        Optional<Showtime> showtime = ShowtimeRepository.findById(bookingDto.getShowtimeId());

        Optional<Seat> seat = SeatRepository.findById(bookingDto.getSeatId());

        Booking booking = BookingMapper.toBookingEntity(bookingDto, customer.isPresent() ? customer.get() : null,
                showtime.isPresent() ? showtime.get() : null, seat.isPresent() ? seat.get() : null);

        bookingRepository.save(booking);

        return booking.getBookingId();
    }

    @Override
    public Long cancelBooking(Long bookingId) {
        bookingRepository.delete(bookingRepository.findById(bookingId).get());
        return bookingId;
    }

    @Override
    public String makePayment(PaymentDto paymentDto) {
        Payment payment = PaymentMapper.toPaymentEntity(bookingRepository.findById(paymentDto.getBookingId()), paymentDto);
        payment.setPaymentStatus("PAID");
        paymentRepository.save(payment);
        return payment.getPaymentStatus();
    }

    @Override
    public CustomerDto getCustomerDetails(Long customerId) {
        return CustomerMapper.toCustomerDto(customerRepository.findById(customerId).get());
    }


}
