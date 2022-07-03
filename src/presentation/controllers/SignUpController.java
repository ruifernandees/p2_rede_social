package presentation.controllers;

import java.util.Scanner;

import domain.dtos.CreateUserDTO;
import domain.exceptions.SignUpException;
import domain.repositories.implementations.MemoryUsersRepository;
import domain.usecases.SignUpUseCase;

public class SignUpController {
    public static void execute(Scanner reader) {
        System.out.print("Informe o nome de usuário: ");
        reader.nextLine();
        String username = reader.nextLine();
        System.out.print("Informe o login: ");
        String login = reader.nextLine();
        System.out.print("Informe a senha: ");
        String password = reader.nextLine();
        SignUpUseCase signUpUseCase = new SignUpUseCase(new MemoryUsersRepository());
        try {
            signUpUseCase.execute(new CreateUserDTO(username, login, password));
            System.out.println("Conta criada com sucesso! Faça login.");
        } catch (SignUpException e) {
            System.out.println(e.getMessage());
        }
    }
}
