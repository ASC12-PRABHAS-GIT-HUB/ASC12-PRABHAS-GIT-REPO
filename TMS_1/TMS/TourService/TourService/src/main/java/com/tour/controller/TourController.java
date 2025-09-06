package com.tour.controller;


import com.tour.dto.TourRequest;
import com.tour.dto.TourResponse;
import com.tour.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;

    @PostMapping
    public ResponseEntity<TourResponse> createTour(@RequestBody TourRequest request) {
        TourResponse res = tourService.createTour(request);
        return ResponseEntity.status(201).body(res);
    }

    @GetMapping
    public ResponseEntity<List<TourResponse>> listTours() {
        return ResponseEntity.ok(tourService.getAllTours());
    }

    @GetMapping("/{code}")
    public ResponseEntity<TourResponse> getTour(@PathVariable("code") String code) {
        return ResponseEntity.ok(tourService.getTourByCode(code));
    }

    @PutMapping("/{code}")
    public ResponseEntity<TourResponse> updateTour(@PathVariable("code") String code, @RequestBody TourRequest request) {
        return ResponseEntity.ok(tourService.updateTour(code, request));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteTour(@PathVariable("code") String code) {
        return ResponseEntity.ok(tourService.deleteTour(code));
    }
}
