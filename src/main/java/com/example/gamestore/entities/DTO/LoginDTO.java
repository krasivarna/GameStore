package com.example.gamestore.entities.DTO;

public class LoginDTO {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LoginDTO(String[] commandLineParts){
        this.email=commandLineParts[1];
        this.password=commandLineParts[2];
    }
}
