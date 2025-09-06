package com.hotel.service;



import com.hotel.dto.HotelRequest;
import com.hotel.dto.HotelResponse;

import java.util.List;

public interface HotelService {
    HotelResponse createHotel(HotelRequest request);
    HotelResponse getHotelByCode(String hotelCode);
    List<HotelResponse> getAllHotels();
    HotelResponse updateHotel(String hotelCode, HotelRequest request);
    String deleteHotel(String hotelCode);
}


