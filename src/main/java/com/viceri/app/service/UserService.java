package com.viceri.app.service;

import com.viceri.app.dto.UserDTO;
import com.viceri.app.dto.UserRequestDTO;
import com.viceri.app.model.User;
import com.viceri.app.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserRequestDTO userRequestDTO) {
        var userFound = userRepository.findByEmail(userRequestDTO.getEmail());
        if (userFound.isPresent()) {
            throw new EntityExistsException("Email already being used");
        }
        User user = new User(userRequestDTO.getName(), userRequestDTO.getEmail(), userRequestDTO.getPassword());
        userRepository.save(user);
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }
}