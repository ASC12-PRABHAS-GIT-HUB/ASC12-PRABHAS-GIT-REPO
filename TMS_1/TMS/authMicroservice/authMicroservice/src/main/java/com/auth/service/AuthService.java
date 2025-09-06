package com.auth.service;


import com.auth.dto.AdminDto;
import com.auth.dto.LoginRequest;
import com.auth.dto.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);
    String login(LoginRequest request);
    AdminDto getAdmin(String username);
}

