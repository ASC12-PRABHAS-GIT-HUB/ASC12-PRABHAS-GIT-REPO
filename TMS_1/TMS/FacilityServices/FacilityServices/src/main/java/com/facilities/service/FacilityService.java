package com.facilities.service;

import com.facilities.dto.FacilityRequest;
import com.facilities.dto.FacilityResponse;

import java.util.List;

public interface FacilityService {
    FacilityResponse createFacility(FacilityRequest request);
    FacilityResponse getFacilityById(Long id);
    List<FacilityResponse> getFacilitiesByHotel(Long hotelId);
    FacilityResponse updateFacility(Long id, FacilityRequest request);
    String deleteFacility(Long id);
    List<FacilityResponse> getAllFacilities();
}

