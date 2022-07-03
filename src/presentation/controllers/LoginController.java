package presentation.controllers;

import java.util.Scanner;

import domain.exceptions.LoginException;
import domain.repositories.implementations.MemoryUsersRepository;
import domain.usecases.LoginUseCase;

public class LoginController {
    public static void execute(Scanner reader)  {
        System.out.print("Informe o login: ");
        String login = reader.next();
        System.out.print("Informe a senha: ");
        String pwd = reader.next();
        try {
            LoginUseCase loginUseCase = new LoginUseCase(new MemoryUsersRepository());
            loginUseCase.execute(login, pwd);
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }
    }
}
