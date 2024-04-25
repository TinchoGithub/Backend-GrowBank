package com.c1736.userservice.service;


import com.c1736.userservice.configuration.security.dto.JwtTokenResponseDto;
import com.c1736.userservice.configuration.security.dto.LoginRequestDto;

public interface IAuthHandler {
    JwtTokenResponseDto loginUser(LoginRequestDto loginRequestDto);
}
