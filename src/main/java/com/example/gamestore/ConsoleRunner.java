package com.example.gamestore;

import com.example.gamestore.exceptions.ValidateException;
import com.example.gamestore.services.ExecutorService;
import com.example.gamestore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ExecutorService executorService;

    @Autowired
    public ConsoleRunner(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void run(String... args) {
        Scanner sc=new Scanner(System.in);
        String command =sc.nextLine();
        String result;
        try {
            result=executorService.execute(command);
        } catch (ValidateException e) {
            result=e.getMessage();
        }

        System.out.println(result);
    }
}
