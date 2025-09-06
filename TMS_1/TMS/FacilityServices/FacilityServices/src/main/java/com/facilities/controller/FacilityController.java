package com.facilities.controller;

import com.facilities.dto.FacilityRequest;
import com.facilities.dto.FacilityResponse;
import com.facilities.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    // New endpoint to get all facilities
    @GetMapping
    public ResponseEntity<List<FacilityResponse>> getAllFacilities() {
        return ResponseEntity.ok(facilityService.getAllFacilities());
    }

    @PostMapping
    public ResponseEntity<FacilityResponse> createFacility(@RequestBody FacilityRequest request) {
        return ResponseEntity.status(201).body(facilityService.createFacility(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityResponse> getFacility(@PathVariable Long id) {
        return ResponseEntity.ok(facilityService.getFacilityById(id));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<FacilityResponse>> getFacilitiesByHotel(@PathVariable Long hotelId) {
        return ResponseEntity.ok(facilityService.getFacilitiesByHotel(hotelId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacilityResponse> updateFacility(@PathVariable Long id, @RequestBody FacilityRequest request) {
        return ResponseEntity.ok(facilityService.updateFacility(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFacility(@PathVariable Long id) {
        return ResponseEntity.ok(facilityService.deleteFacility(id));
    }
}