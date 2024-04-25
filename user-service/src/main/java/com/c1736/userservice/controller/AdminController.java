package com.c1736.userservice.controller;

import com.c1736.userservice.entities.User;
import com.c1736.userservice.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final IUserService userService;

    public AdminController(IUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Buscar usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping(path = "/findUser/{id}")
    public User findUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @Operation(summary = "Buscar todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios encontrados", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se encontraron usuarios")
    })
    @GetMapping("/findAllUser")
    public List<User>findAllUsers(){
        return userService.findAllUsers();
    }

    @Operation(summary = "Actualizar usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/updateUser/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody User user){
        return userService.updateUserById(id, user);
    }

    @Operation(summary = "Eliminar usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/deleteUser/{Id}")
    public void deleteUserById(@PathVariable Long Id){
        userService.deleteUserById(Id);
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
