package com.viceri.app.service;

import com.viceri.app.dto.UserDTO;
import com.viceri.app.dto.UserRequestDTO;
import com.viceri.app.model.User;
import com.viceri.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User(userRequestDTO.getName(), userRequestDTO.getEmail(), userRequestDTO.getPassword());
        userRepository.save(user);
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }
}
