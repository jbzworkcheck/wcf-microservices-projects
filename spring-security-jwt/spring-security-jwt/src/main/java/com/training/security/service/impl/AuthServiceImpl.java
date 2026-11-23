package com.training.security.service.impl;

import com.training.security.dto.JwtResponse;
import com.training.security.dto.LoginRequest;
import com.training.security.dto.RegisterRequest;
import com.training.security.entity.ERole;
import com.training.security.entity.Role;
import com.training.security.entity.User;
import com.training.security.exception.BadRequestException;
import com.training.security.repository.RoleRepository;
import com.training.security.repository.UserRepository;
import com.training.security.security.JwtUtil;
import com.training.security.security.UserPrincipal;
import com.training.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username is already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email is already in use");
        }

        Set<Role> roles = resolveRoles(request.getRoles());

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .roles(roles)
                .build();

        userRepository.save(user);
    }

    private Set<Role> resolveRoles(Set<String> requested) {
        Set<Role> roles = new HashSet<>();

        if (requested == null || requested.isEmpty()) {
            roles.add(roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new BadRequestException("ROLE_USER is not configured")));
            return roles;
        }

        for (String r : requested) {
            switch (r.toLowerCase()) {
                case "admin" -> roles.add(roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new BadRequestException("ROLE_ADMIN is not configured")));
                case "user" -> roles.add(roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new BadRequestException("ROLE_USER is not configured")));
                default -> throw new BadRequestException("Unknown role: " + r);
            }
        }
        return roles;
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        String token = jwtUtil.generateToken(principal.getUsername(), List.copyOf(principal.getAuthorities()));

        List<String> roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return JwtResponse.builder()
                .accessToken(token)
                .id(principal.getId())
                .username(principal.getUsername())
                .email(principal.getEmail())
                .roles(roles)
                .build();
    }
}
