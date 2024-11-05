package com.ultraPlexMovieBookingSystem.controller;

import com.ultraPlexMovieBookingSystem.constants.BookingConstants;
import com.ultraPlexMovieBookingSystem.dto.BookingDto;
import com.ultraPlexMovieBookingSystem.dto.CustomerDto;
import com.ultraPlexMovieBookingSystem.dto.PaymentDto;
import com.ultraPlexMovieBookingSystem.dto.ResponseDto;
import com.ultraPlexMovieBookingSystem.service.MoovieBookingService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.ultraPlexMovieBookingSystem.constants.BookingConstants.BOOKING_ID;
import static com.ultraPlexMovieBookingSystem.constants.BookingConstants.CUSTOMER_ID;
import static com.ultraPlexMovieBookingSystem.constants.BookingConstants.PAYMENT_STATUS;

@RestController
@RequestMapping("/ultraplex/movie/booking")
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
public class MovieBookingController {

    @Autowired
    MoovieBookingService movieBookingService;


    @PostMapping(value = "/bookingsForMovies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> bookingsForMovies(@RequestHeader(value = "X-API-KEY") String apiKey, @RequestBody BookingDto bookingDto) {
        Long bookingId = movieBookingService.bookingsForMovies(bookingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(BookingConstants.STATUS_201,
                BookingConstants.BOOKING_STATUS_MESSAGE_201,
                BOOKING_ID + bookingId));
    }

    @PostMapping(value = "/cancelBooking", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> cancelBooking(@RequestHeader(value = "X-API-KEY") String apiKey, @RequestParam(name = "bookingId") Long bookingId) {
        Long bookingIdValue = movieBookingService.cancelBooking(bookingId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(BookingConstants.STATUS_200,
                BookingConstants.CANCEL_BOOKING_STATUS_MESSAGE_200, BOOKING_ID+bookingIdValue));
    }

    @PostMapping(value = "/registerCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> registerCustomer(@RequestHeader(value = "X-API-KEY") String apiKey,
                                                        @RequestBody CustomerDto customerDto) {
        Long customerId = movieBookingService.registerCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(BookingConstants.STATUS_201,
                BookingConstants.CUSTOMER_STATUS_MESSAGE_201, CUSTOMER_ID+customerId));
    }

    @GetMapping(value = "/getCustomerDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> getCustomerDetails(@RequestHeader(value = "X-API-KEY") String apiKey, @RequestParam(name = "customerId") Long customerId) {
        Optional<CustomerDto> customerDto = Optional.ofNullable(movieBookingService.getCustomerDetails(customerId));
        return ResponseEntity.status(HttpStatus.OK).body(customerDto.isPresent() ? customerDto.get() : null);
    }

    @PostMapping(value = "/makePayment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> makePayment(@RequestHeader(value = "X-API-KEY") String apiKey, @RequestBody PaymentDto paymentDto) {
        String paymentStatus = movieBookingService.makePayment(paymentDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(BookingConstants.STATUS_200,
                BookingConstants.PAYMENT_STATUS_SUCCESS_200, PAYMENT_STATUS+paymentStatus));
    }

}
