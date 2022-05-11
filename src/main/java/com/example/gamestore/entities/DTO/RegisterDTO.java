package com.example.gamestore.entities.DTO;

import com.example.gamestore.exceptions.ValidateException;

public class RegisterDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    /**
     * commandParts[0] skipped
     */

    public RegisterDTO(String[] commandParts) throws ValidateException {
        this.email=commandParts[1];
        this.password=commandParts[2];
        this.confirmPassword=commandParts[3];
        this.fullName=commandParts[4];

        this.validate();
    }

    private void validate() throws ValidateException {
        int indexOfAt=email.indexOf("@");
        int indexOfDot=email.lastIndexOf(".");
        if (indexOfAt<0 || indexOfDot<0 || indexOfAt>indexOfDot){
            throw new ValidateException("Email must contain @ and .");
        }

        if (!password.equals(confirmPassword)) {
            throw new ValidateException("Password and confirm, password must match!");
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }
}
