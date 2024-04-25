package com.c1736.userservice.controller;

import com.c1736.userservice.entities.User;
import com.c1736.userservice.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final IUserService userService;

    public CompanyController(IUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Guardar nuevo usuario como empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario guardado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "409", description = "Conflicto - Usuario ya existe")
    })
    @PostMapping("/saveUser")
    public void saveUser(@Valid @RequestBody User user){
        userService.saveUserCompany(user);
    }

    @Operation(summary = "Obtener usuario por correo electrónico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/getUser")
    public ResponseEntity<User> getUserByEmail(@RequestParam("email") String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
