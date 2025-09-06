package com.hotel.service;



import com.hotel.dto.HotelRequest;
import com.hotel.dto.HotelResponse;
import com.hotel.entity.Hotel;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public HotelResponse createHotel(HotelRequest request) {
        Hotel hotel = Hotel.builder()
                .name(request.getName())
                .city(request.getCity())
                .pricePerNight(request.getPricePerNight())
                .rating(request.getRating())
                .deleted(false)
                .hotelCode("HTL-" + UUID.randomUUID().toString().substring(0, 8))
                .build();

        hotelRepository.save(hotel);
        return mapToResponse(hotel);
    }

    @Override
    public HotelResponse getHotelByCode(String hotelCode) {
        Hotel hotel = hotelRepository.findByHotelCode(hotelCode)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with code " + hotelCode));
        return mapToResponse(hotel);
    }

    @Override
    public List<HotelResponse> getAllHotels() {
        return hotelRepository.findAll().stream()
                .filter(h -> !h.isDeleted())
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public HotelResponse updateHotel(String hotelCode, HotelRequest request) {
        Hotel hotel = hotelRepository.findByHotelCode(hotelCode)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with code " + hotelCode));

        hotel.setName(request.getName());
        hotel.setCity(request.getCity());
        hotel.setPricePerNight(request.getPricePerNight());
        hotel.setRating(request.getRating());

        hotelRepository.save(hotel);
        return mapToResponse(hotel);
    }

    @Override
    public String deleteHotel(String hotelCode) {
        Hotel hotel = hotelRepository.findByHotelCode(hotelCode)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with code " + hotelCode));

        hotel.setDeleted(true);
        hotelRepository.save(hotel);
        return "Hotel with code " + hotelCode + " deleted successfully.";
    }

    private HotelResponse mapToResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .city(hotel.getCity())
                .pricePerNight(hotel.getPricePerNight())
                .rating(hotel.getRating())
                .hotelCode(hotel.getHotelCode())
                .build();
    }
}

