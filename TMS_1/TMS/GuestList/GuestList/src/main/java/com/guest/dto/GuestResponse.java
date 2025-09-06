package com.guest.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
