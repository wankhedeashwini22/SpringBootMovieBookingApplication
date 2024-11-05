package com.ultraPlexMovieBookingSystem.service;
import com.ultraPlexMovieBookingSystem.dto.BookingDto;
import com.ultraPlexMovieBookingSystem.dto.CustomerDto;
import com.ultraPlexMovieBookingSystem.dto.PaymentDto;
import com.ultraPlexMovieBookingSystem.entity.Booking;
import com.ultraPlexMovieBookingSystem.entity.Customer;
import com.ultraPlexMovieBookingSystem.entity.Payment;
import com.ultraPlexMovieBookingSystem.entity.Seat;
import com.ultraPlexMovieBookingSystem.entity.Showtime;
import com.ultraPlexMovieBookingSystem.repository.BookingRepository;
import com.ultraPlexMovieBookingSystem.repository.CustomerRepository;
import com.ultraPlexMovieBookingSystem.repository.PaymentRepository;
import com.ultraPlexMovieBookingSystem.repository.SeatRepository;
import com.ultraPlexMovieBookingSystem.repository.ShowtimeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieBookingServiceImplTest {

    @Mock
    BookingRepository bookingRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    ShowtimeRepository ShowtimeRepository;

    @Mock
    SeatRepository SeatRepository;

    @Mock
    PaymentRepository paymentRepository;

    @InjectMocks
    private MovieBookingServiceImpl movieBookingServiceImplTest;

    @Test
    public void movieBookingServiceImplTest_Success() throws Exception {

        when(customerRepository.findById(anyLong()))
        .thenReturn(Optional.ofNullable(Customer.builder().customerId(1L).email("email").name("name").password("password").build()));

        when(ShowtimeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(Showtime.builder().showtimeId(1L).build()));

        when(SeatRepository.findById(anyLong())).thenReturn(Optional.ofNullable(Seat.builder().seatId(1L).build()));

        when(bookingRepository.save(any(Booking.class))).thenReturn(Booking.builder().bookingId(16778897L).build());

        Long bookingId = movieBookingServiceImplTest.bookingsForMovies(BookingDto.builder().bookingId(16778897L).customerId(1L).showtimeId(1L).seatId(1L)
                .totalAmount(20).bookingTime(LocalDateTime.now()).build());

        assertNotNull(bookingId);
        assertEquals(16778897L, bookingId);

    }

    @Test
    public void movieBookingServiceImplTest_Fail() throws Exception {
        assertThrows(RuntimeException.class, () -> movieBookingServiceImplTest.bookingsForMovies(null));
    }

    @Test
    public void movieBookingServiceImplTest_registerCustomer_Success() throws Exception {

        when(customerRepository.save(any(Customer.class))).thenReturn(Customer.builder().customerId(102L).build());

        Long customerId = movieBookingServiceImplTest.registerCustomer(CustomerDto.builder().customerId(102L).email("email").name("name").phone("phone").build());

        assertNotNull(customerId);

        assertEquals(102L, customerId);

        verify(customerRepository, times(1)).save(any(Customer.class));
        verify(bookingRepository, times(0)).save(any(Booking.class));
    }

    @Test
    public void movieBookingServiceImplTest_cancelBooking_Success() throws Exception {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.ofNullable(Booking.builder().bookingId(1L).build()));
        Long bookingId = movieBookingServiceImplTest.cancelBooking(303L);
        assertNotNull(bookingId);
        assertEquals(303L, bookingId);
        verify(bookingRepository, times(1)).delete(any(Booking.class));
    }

    @Test
    public void movieBookingServiceImplTest_makePayment_Success() throws Exception {
        String paymentStatus = movieBookingServiceImplTest.makePayment(PaymentDto.builder().paymentId(100L).build());
        assertNotNull(paymentStatus);
        assertEquals("PAID", paymentStatus);
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    public void movieBookingServiceImplTest_getCustomerDetail_Fail() throws Exception {
        assertThrows(RuntimeException.class, () -> movieBookingServiceImplTest.getCustomerDetails(null));
    }

    @Test
    public void movieBookingServiceImplTest_getCustomerDetail_Exception() throws Exception {
        assertThrows(RuntimeException.class, () -> movieBookingServiceImplTest.getCustomerDetails(null));
    }

    @Test
    public void movieBookingServiceImplTest_getCustomerDetail_Success() throws Exception {

        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(Customer.builder().customerId(23L).email("email").name("name").password("password").build()));
        CustomerDto customerDto = movieBookingServiceImplTest.getCustomerDetails(anyLong());
        assertNotNull(customerDto);
        assertEquals(23L, customerDto.getCustomerId());
        verify(customerRepository, times(1)).findById(anyLong());
        verifyNoInteractions(bookingRepository);
    }

}