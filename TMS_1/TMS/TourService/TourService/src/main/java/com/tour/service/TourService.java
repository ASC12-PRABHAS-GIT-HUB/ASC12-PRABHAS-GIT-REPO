package com.tour.service;


import com.tour.dto.TourRequest;
import com.tour.dto.TourResponse;

import java.util.List;

public interface TourService {
    TourResponse createTour(TourRequest request);
    TourResponse getTourByCode(String tourCode);
    List<TourResponse> getAllTours();
    TourResponse updateTour(String tourCode, TourRequest request);
    String deleteTour(String tourCode);
}

