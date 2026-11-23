package com.training.security.service.impl;

import com.training.security.dto.ApiResponse;
import com.training.security.entity.Role;
import com.training.security.entity.User;
import com.training.security.exception.ResourceNotFoundException;
import com.training.security.repository.UserRepository;
import com.training.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<Map<String, Object>> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toSummary)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return toSummary(user);
    }

    @Override
    @Transactional
    public ApiResponse deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
        return new ApiResponse(true, "User deleted successfully");
    }

    @Override
    @Transactional
    public ApiResponse setEnabled(Long id, boolean enabled) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setEnabled(enabled);
        userRepository.save(user);
        return new ApiResponse(true, "User " + (enabled ? "enabled" : "disabled") + " successfully");
    }

    private Map<String, Object> toSummary(User user) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        map.put("email", user.getEmail());
        map.put("enabled", user.isEnabled());
        map.put("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        map.put("createdAt", user.getCreatedAt());
        return map;
    }
}
