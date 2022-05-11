package com.example.gamestore.services;

import com.example.gamestore.entities.DTO.LoginDTO;
import com.example.gamestore.entities.DTO.RegisterDTO;
import com.example.gamestore.entities.User;

import java.util.Optional;

public interface UserService {
    User register(RegisterDTO registerData);

    Optional<User> login(LoginDTO loginDTO);

    void logout();

    User getLoggedUser();
}
