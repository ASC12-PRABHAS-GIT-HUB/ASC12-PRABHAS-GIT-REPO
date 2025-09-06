package com.guest.service;



import com.guest.dto.GuestRequest;
import com.guest.dto.GuestResponse;
import com.guest.entity.Guest;
import com.guest.exception.GuestNotFoundException;
import com.guest.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;

    @Override
    public GuestResponse createGuest(GuestRequest request) {
        Guest guest = Guest.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .deleted(false)
                .build();

        guestRepository.save(guest);
        return mapToResponse(guest);
    }

    @Override
    public GuestResponse getGuestById(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new GuestNotFoundException("Guest not found with id " + id));
        return mapToResponse(guest);
    }

    @Override
    public List<GuestResponse> getAllGuests() {
        return guestRepository.findAll().stream()
                .filter(g -> !g.isDeleted())
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GuestResponse updateGuest(Long id, GuestRequest request) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new GuestNotFoundException("Guest not found with id " + id));

        guest.setFirstName(request.getFirstName());
        guest.setLastName(request.getLastName());
        guest.setEmail(request.getEmail());
        guest.setPhone(request.getPhone());

        guestRepository.save(guest);
        return mapToResponse(guest);
    }

    @Override
    public void deleteGuest(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new GuestNotFoundException("Guest not found with id " + id));
        guest.setDeleted(true);
        guestRepository.save(guest);
    }

    private GuestResponse mapToResponse(Guest guest) {
        return GuestResponse.builder()
                .id(guest.getId())
                .firstName(guest.getFirstName())
                .lastName(guest.getLastName())
                .email(guest.getEmail())
                .phone(guest.getPhone())
                .build();
    }
}

