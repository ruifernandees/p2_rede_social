package presentation.controllers;

import java.util.ArrayList;

import domain.entities.User;
import domain.repositories.implementations.MemoryUsersRepository;
import domain.usecases.FindAllUsersUseCase;

public class ShowAllUsersController {
    public static void execute() {
        FindAllUsersUseCase findAllUsersUseCase = new FindAllUsersUseCase(new MemoryUsersRepository());
        ArrayList<User> allUsers = findAllUsersUseCase.execute();
        for (User user : allUsers) {
            System.out.println(user); 
        }
    }
}
