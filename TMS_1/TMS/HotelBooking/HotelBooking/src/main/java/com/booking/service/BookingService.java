package com.booking.service;

import com.booking.dto.BookingRequest;
import com.booking.dto.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest request);
    BookingResponse getBooking(Long id);
    List<BookingResponse> getAllBookings();
    BookingResponse cancelBooking(Long id);
}
