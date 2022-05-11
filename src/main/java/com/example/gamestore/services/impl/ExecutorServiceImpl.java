package com.example.gamestore.services.impl;

import com.example.gamestore.entities.DTO.LoginDTO;
import com.example.gamestore.entities.DTO.RegisterDTO;
import com.example.gamestore.entities.User;
import com.example.gamestore.services.ExecutorService;
import com.example.gamestore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecutorServiceImpl implements ExecutorService {
    private final UserService userService;

    @Autowired
    public ExecutorServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(String commandLine) {
        String[] commandParts=commandLine.split("\\|");

        String commandName=commandParts[0];

        String commandOutput=switch (commandName) {
            case REGISTER_USER_COMMAND ->  registerUser(commandParts);
            case LOGIN_USER_COMMAND -> loginUser(commandParts);
            case LOGOUT_USER_COMMAND -> logoutUser();
            case ADD_GAME_COMMAND -> addGame();
            default -> "unknown command";
        };
        return commandOutput;
    }

    private String addGame() {
        User loggedUser=this.userService.getLoggedUser();

        if (!loggedUser.isAdmin()){

        }
        return "";
    }

    private String logoutUser() {
        User user = this.userService.getLoggedUser();
        this.userService.logout();
        return String.format("Successfully logout %s", user.getFullName());
    }

    private String loginUser(String[] commandParts) {
        LoginDTO loginDTO=new LoginDTO(commandParts);
        Optional<User> user=userService.login(loginDTO);
        if (user.isPresent()) {
            return String.format("Successfully logged in %s", user.get().getFullName());
        }else{
            return String.format("Wrong credentials");
        }
    }

    private String registerUser(String[] commandParts) {
        RegisterDTO registerData = new RegisterDTO(commandParts);
        User user = userService.register(registerData);
        return String.format("%s was registered!", user.getFullName());
    }
}
