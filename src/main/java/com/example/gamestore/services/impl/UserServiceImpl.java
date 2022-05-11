package com.example.gamestore.services.impl;

import com.example.gamestore.entities.DTO.LoginDTO;
import com.example.gamestore.entities.DTO.RegisterDTO;
import com.example.gamestore.entities.User;
import com.example.gamestore.repository.UserRepository;
import com.example.gamestore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private User currentUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.currentUser=null;
    }

    @Override
    public User register(RegisterDTO registerData) {

        ModelMapper mapper=new ModelMapper();
        User toRegister=mapper.map(registerData,User.class);
        long userCount=this.userRepository.count();

        if (userCount==0){
            toRegister.setAdmin(true);
        }
        return this.userRepository.save(toRegister);
    }

    @Override
    public Optional<User> login(LoginDTO loginData) {
        Optional<User> user=this.userRepository.findByEmailAndPassword(loginData.getEmail(),loginData.getPassword());

        if (user.isPresent()){
            this.currentUser=user.get();
        }
        return user;
    }

    @Override
    public void logout() {
       this.currentUser=null;
    }

    @Override
    public User getLoggedUser() {
        return this.currentUser;
    }
}
