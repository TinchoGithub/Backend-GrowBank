package com.c1736.userservice.service.impl;

import com.c1736.userservice.client.IMessagingFeignClient;
import com.c1736.userservice.client.dto.MessagingDTO;
import com.c1736.userservice.configuration.Constants;
import com.c1736.userservice.entities.User;
import com.c1736.userservice.repository.IRoleRepository;
import com.c1736.userservice.repository.IUserRepository;
import com.c1736.userservice.service.IAuthPasswordEncoderPort;
import com.c1736.userservice.service.IUserService;
import jakarta.transaction.Transactional;
import com.c1736.userservice.service.exceptions.UserAlreadyExistsException;
import com.c1736.userservice.service.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static com.c1736.userservice.configuration.Constants.NEW_CLIENT;
import static com.c1736.userservice.configuration.Constants.NEW_COMPANY;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IAuthPasswordEncoderPort authPasswordEncoderPort;
    private final IMessagingFeignClient messagingFeignClient;

    public UserServiceImpl(IUserRepository userRepository, IRoleRepository roleRepository, IAuthPasswordEncoderPort authPasswordEncoderPort, IMessagingFeignClient messagingFeignClient) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authPasswordEncoderPort = authPasswordEncoderPort;
        this.messagingFeignClient = messagingFeignClient;
    }


    /**
     * Guarda un usuario con el rol de cliente en la base de datos.
     *
     * @param user El usuario a guardar.
     * @throws UserAlreadyExistsException Si ya existe un usuario con el mismo correo electrónico.
     */
    @Override
    public void saveUserClient(User user) {
        String email = user.getEmail();
        if (userRepository.existsByEmail(email)){
            throw new UserAlreadyExistsException();
        }
        user.setRole(roleRepository.findByName("ROLE_CLIENT"));
        user.setPassword(authPasswordEncoderPort.encodePassword(user.getPassword()));
        userRepository.save(user);

//        MessagingDTO dto = new MessagingDTO(user.getEmail(), "CLIENTE CREADO EXITOSAMENTE", NEW_CLIENT);
//        messagingFeignClient.sendEmail(dto);

    }

    /**
     * Guarda un usuario con el rol de empresa en la base de datos.
     *
     * @param user El usuario a guardar.
     * @throws UserAlreadyExistsException Si ya existe un usuario con el mismo correo electrónico.
     */
    @Override
    public void saveUserCompany(User user) {
        String email = user.getEmail();
        if (userRepository.existsByEmail(email)){
            throw new UserAlreadyExistsException();
        }
        user.setRole(roleRepository.findByName("ROLE_COMPANY"));
        user.setPassword(authPasswordEncoderPort.encodePassword(user.getPassword()));
        userRepository.save(user);
        // Mantiene el comentario
//        MessagingDTO messageDto = new MessagingDTO(user.getEmail(), "COMPAÑÍA CREADA EXITOSAMENTE", NEW_COMPANY);
//        messagingFeignClient.sendEmail(messageDto);

    }

    /**
     * Encuentra un usuario por su ID.
     *
     * @param id El ID del usuario.
     * @return El usuario encontrado.
     * @throws UserNotFoundException Si no se encuentra el usuario con el ID especificado.
     */
    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    /**
     * Obtiene todos los usuarios de la base de datos.
     *
     * @return Una lista de todos los usuarios.
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Actualiza un usuario por su ID.
     *
     * @param id   El ID del usuario a actualizar.
     * @param user Los datos actualizados del usuario.
     * @return El usuario actualizado.
     * @throws UserNotFoundException Si no se encuentra el usuario con el ID especificado.
     */
    @Override
    public User updateUserById(Long id, User user) {
        User userExist = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userExist.setFirstName(user.getFirstName());
        userExist.setLastName(user.getLastName());
        userExist.setPhone(user.getPhone());
        userExist.setEmail(user.getEmail());
        userExist.setPassword(authPasswordEncoderPort.encodePassword(user.getPassword()));
        userExist.setRole(user.getRole());

        return userRepository.save(userExist);
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id El ID del usuario a eliminar.
     * @throws UserNotFoundException Si no se encuentra el usuario con el ID especificado.
     */
    @Override
    public void deleteUserById(Long id) {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }

    /**
     * Busca un usuario por su dirección de correo electrónico.
     *
     * @param email La dirección de correo electrónico del usuario a buscar.
     * @return Un objeto Optional que contiene el usuario encontrado.
     * @throws UserNotFoundException Si no se encuentra el usuario asociado al correo electrónico.
     */
    @Override
    public Optional<User> findByEmail(String email) {
        if (!userRepository.existsByEmail(email)){
            throw new UserNotFoundException();
        } else {
            return Optional.ofNullable(userRepository.findByEmail(email));
        }
    }
}
