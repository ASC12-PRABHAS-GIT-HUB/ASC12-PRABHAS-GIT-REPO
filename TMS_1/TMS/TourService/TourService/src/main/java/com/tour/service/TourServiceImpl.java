package com.tour.service;



import com.tour.dto.TourRequest;
import com.tour.dto.TourResponse;
import com.tour.entity.Tour;
import com.tour.exception.TourNotFoundException;
import com.tour.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    @Override
    public TourResponse createTour(TourRequest request) {
        Tour tour = Tour.builder()
                .tourCode("TR-" + UUID.randomUUID().toString().substring(0, 8))
                .name(request.getName())
                .description(request.getDescription())
                .durationDays(request.getDurationDays())
                .price(request.getPrice())
                .deleted(false)
                .build();

        tourRepository.save(tour);
        return mapToResponse(tour);
    }

    @Override
    public TourResponse getTourByCode(String tourCode) {
        Tour tour = tourRepository.findByTourCode(tourCode)
                .orElseThrow(() -> new TourNotFoundException("Tour not found with code " + tourCode));
        return mapToResponse(tour);
    }

    @Override
    public List<TourResponse> getAllTours() {
        return tourRepository.findAll().stream()
                .filter(t -> !t.isDeleted())
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TourResponse updateTour(String tourCode, TourRequest request) {
        Tour tour = tourRepository.findByTourCode(tourCode)
                .orElseThrow(() -> new TourNotFoundException("Tour not found with code " + tourCode));

        tour.setName(request.getName());
        tour.setDescription(request.getDescription());
        tour.setDurationDays(request.getDurationDays());
        tour.setPrice(request.getPrice());

        tourRepository.save(tour);
        return mapToResponse(tour);
    }

    @Override
    public String deleteTour(String tourCode) {
        Tour tour = tourRepository.findByTourCode(tourCode)
                .orElseThrow(() -> new TourNotFoundException("Tour not found with code " + tourCode));
        tour.setDeleted(true);
        tourRepository.save(tour);
        return "Tour with code " + tourCode + " marked as deleted.";
    }

    private TourResponse mapToResponse(Tour tour) {
        return TourResponse.builder()
                .id(tour.getId())
                .tourCode(tour.getTourCode())
                .name(tour.getName())
                .description(tour.getDescription())
                .durationDays(tour.getDurationDays())
                .price(tour.getPrice())
                .build();
    }
}
