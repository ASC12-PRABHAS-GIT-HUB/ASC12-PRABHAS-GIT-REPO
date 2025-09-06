package com.guest.service;



import com.guest.dto.GuestRequest;
import com.guest.dto.GuestResponse;

import java.util.List;

public interface GuestService {
    GuestResponse createGuest(GuestRequest request);
    GuestResponse getGuestById(Long id);
    List<GuestResponse> getAllGuests();
    GuestResponse updateGuest(Long id, GuestRequest request);
    void deleteGuest(Long id);
}

