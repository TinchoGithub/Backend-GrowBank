package com.c1736.userservice.controller;

import com.c1736.userservice.configuration.security.dto.JwtTokenResponseDto;
import com.c1736.userservice.configuration.security.dto.LoginRequestDto;
import com.c1736.userservice.service.IAuthHandler;
import com.c1736.userservice.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final IUserService userService;
    private final IAuthHandler authHandler;

    @Operation(summary = "Login into the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized for bad credentials", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponseDto> loginUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(this.authHandler.loginUser(loginRequestDto), HttpStatus.OK);
    }
}

