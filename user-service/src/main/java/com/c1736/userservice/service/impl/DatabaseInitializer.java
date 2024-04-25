package com.c1736.userservice.service.impl;

import com.c1736.userservice.entities.Role;
import com.c1736.userservice.entities.User;
import com.c1736.userservice.repository.IRoleRepository;
import com.c1736.userservice.repository.IUserRepository;
import com.c1736.userservice.service.IAuthPasswordEncoderPort;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {
    private final IUserRepository userRepository;
    private final IAuthPasswordEncoderPort passwordEncoder;
    private final IRoleRepository roleRepository;

    public DatabaseInitializer(IUserRepository userRepository, IAuthPasswordEncoderPort passwordEncoder, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    /**
     * Método que se ejecuta después de la construcción del bean para inicializar
     * roles y el usuario admin en la base de datos.
     */
    @PostConstruct
    public void initialize() {
        initializeRoles();
        initializeAdminUser();
    }

    private void initializeRoles() {
        createRoleIfNotExists("ROLE_ADMIN", "ROLE_ADMIN");
        createRoleIfNotExists("ROLE_COMPANY", "ROLE_COMPANY");
        createRoleIfNotExists("ROLE_CLIENT", "ROLE_CLIENT");
    }

    /**
     * Crea un nuevo rol si no existe en la base de datos.
     *
     * @param name        Nombre del rol.
     * @param description Descripción del rol.
     */
    private void createRoleIfNotExists(String name, String description) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role.setDescription(description);
            roleRepository.save(role);
        }
    }

    private void initializeAdminUser() {
        if (userRepository.findByEmail("admin@mail.com") == null) {
            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("AdminSurname");
            admin.setPhone("91123456789");
            admin.setEmail("admin@mail.com");
            admin.setPassword(passwordEncoder.encodePassword("admin"));

            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            admin.setRole(adminRole);

            userRepository.save(admin);
        }
    }
}
