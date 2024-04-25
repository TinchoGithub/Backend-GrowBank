package com.c1736.userservice.service.impl;

import com.c1736.userservice.configuration.security.dto.JwtTokenResponseDto;
import com.c1736.userservice.configuration.security.dto.LoginRequestDto;
import com.c1736.userservice.configuration.security.jwt.JwtUtils;
import com.c1736.userservice.configuration.security.userDetails.CustomUserDetails;
import com.c1736.userservice.service.IAuthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthHandler implements IAuthHandler {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    /**
     * Login user - Se accede mediante el email y password del usuario que hayas creado
     *
     * @param loginRequestDto Login credentials
     * @return JwtTokenResponseDto
     */
    @Override
    public JwtTokenResponseDto loginUser(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtTokenResponseDto(jwt, userDetails.getUsername(), roles);
    }
}
