package com.facilities.service;

import com.facilities.dto.FacilityRequest;
import com.facilities.dto.FacilityResponse;
import com.facilities.entity.Facility;
import com.facilities.exception.FacilityNotFoundException;
import com.facilities.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;

    @Override
    public FacilityResponse createFacility(FacilityRequest request) {
        Facility facility = Facility.builder()
                .name(request.getName())
                .description(request.getDescription())
                .available(request.isAvailable())
                .hotelId(request.getHotelId())
                .build();

        facilityRepository.save(facility);
        return mapToResponse(facility);
    }

    // New method to get all facilities
    @Override
    public List<FacilityResponse> getAllFacilities() {
        return facilityRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FacilityResponse getFacilityById(Long id) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new FacilityNotFoundException("Facility not found with id " + id));
        return mapToResponse(facility);
    }

    @Override
    public List<FacilityResponse> getFacilitiesByHotel(Long hotelId) {
        return facilityRepository.findByHotelId(hotelId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FacilityResponse updateFacility(Long id, FacilityRequest request) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new FacilityNotFoundException("Facility not found with id " + id));

        facility.setName(request.getName());
        facility.setDescription(request.getDescription());
        facility.setAvailable(request.isAvailable());

        facilityRepository.save(facility);
        return mapToResponse(facility);
    }

    @Override
    public String deleteFacility(Long id) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new FacilityNotFoundException("Facility not found with id " + id));

        facilityRepository.delete(facility);
        return "Facility with id " + id + " deleted successfully.";
    }

    private FacilityResponse mapToResponse(Facility facility) {
        return FacilityResponse.builder()
                .id(facility.getId())
                .name(facility.getName())
                .description(facility.getDescription())
                .available(facility.isAvailable())
                .hotelId(facility.getHotelId())
                .build();
    }
}