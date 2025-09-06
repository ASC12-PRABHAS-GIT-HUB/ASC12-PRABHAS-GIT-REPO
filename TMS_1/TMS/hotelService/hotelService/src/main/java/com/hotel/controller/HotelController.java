package com.hotel.controller;



import com.hotel.dto.HotelRequest;
import com.hotel.dto.HotelResponse;
import com.hotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelResponse> createHotel(@RequestBody HotelRequest request) {
        HotelResponse res = hotelService.createHotel(request);
        return ResponseEntity.status(201).body(res);
    }

    @GetMapping
    public ResponseEntity<List<HotelResponse>> listHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{code}")
    public ResponseEntity<HotelResponse> getHotel(@PathVariable("code") String code) {
        return ResponseEntity.ok(hotelService.getHotelByCode(code));
    }

    @PutMapping("/{code}")
    public ResponseEntity<HotelResponse> updateHotel(@PathVariable("code") String code, @RequestBody HotelRequest request) {
        return ResponseEntity.ok(hotelService.updateHotel(code, request));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteHotel(@PathVariable("code") String code) {
        return ResponseEntity.ok(hotelService.deleteHotel(code));
    }
}

