package com.finance.dashboard.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.finance.dashboard.dto.request.UserRequest;
import com.finance.dashboard.dto.response.UserResponse;
import com.finance.dashboard.entity.User;
import com.finance.dashboard.enums.Role;
import com.finance.dashboard.repository.UserRepository;
import com.finance.dashboard.service.interfaces.UserService;

import lombok.RequiredArgsConstructor;

import com.finance.dashboard.dto.request.UpdateUserRequest;
import com.finance.dashboard.exception.ResourceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    @Override
    public UserResponse register(UserRequest req) {
        if (repo.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        Role role = Role.ROLE_VIEWER;
        if (req.getRole() != null) {
            try {
                String roleStr = req.getRole().toUpperCase();
                if (!roleStr.startsWith("ROLE_")) {
                    roleStr = "ROLE_" + roleStr;
                }
                role = Role.valueOf(roleStr);
            } catch (Exception e) {
                // Default to VIEWER if invalid
            }
        }

        User u = User.builder()
                .email(req.getEmail())
                .password(encoder.encode(req.getPassword()))
                .name(req.getName())
                .role(role)
                .isActive(true)
                .build();

        return mapToResponse(repo.save(u));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return repo.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest req) {
        User user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (req.getRole() != null) {
            user.setRole(req.getRole());
        }
        if (req.getIsActive() != null) {
            user.setIsActive(req.getIsActive());
        }

        return mapToResponse(repo.save(user));
    }

    private UserResponse mapToResponse(User u) {
        return UserResponse.builder()
                .id(u.getId())
                .email(u.getEmail())
                .name(u.getName())
                .role(u.getRole())
                .isActive(u.getIsActive())
                .build();
    }
}

