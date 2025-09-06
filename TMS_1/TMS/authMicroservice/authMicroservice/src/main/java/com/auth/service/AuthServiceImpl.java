package com.auth.service;


import com.auth.dto.AdminDto;
import com.auth.dto.LoginRequest;
import com.auth.dto.RegisterRequest;
import com.auth.entity.AdminUser;
import com.auth.exception.LockedException;
import com.auth.repository.AdminUserRepository;
import com.auth.util.PasswordValidator;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AdminUserRepository adminUserRepository;

    public AuthServiceImpl(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    public String register(RegisterRequest request) {
        if (!PasswordValidator.isValid(request.getPassword())) {
            return "Password is too weak!";
        }

        AdminUser user = new AdminUser();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // ⚠️ in real apps, hash it
        user.setEmail(request.getEmail());
        adminUserRepository.save(user);

        return "Admin registered successfully!";
    }

    @Override
    public String login(LoginRequest request) {
        AdminUser user = adminUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.isLocked()) {
            throw new LockedException("Account is locked");
        }

        if (!user.getPassword().equals(request.getPassword())) {
            return "Invalid credentials!";
        }

        return "Login successful!";
    }

    @Override
    public AdminDto getAdmin(String username) {
        AdminUser user = adminUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new AdminDto(user.getUsername(), user.getEmail());
    }
}

