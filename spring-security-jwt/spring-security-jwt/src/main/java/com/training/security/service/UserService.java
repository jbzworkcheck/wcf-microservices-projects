package com.training.security.service;

import com.training.security.dto.ApiResponse;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<Map<String, Object>> getAllUsers();

    Map<String, Object> getUserById(Long id);

    ApiResponse deleteUser(Long id);

    ApiResponse setEnabled(Long id, boolean enabled);
}
