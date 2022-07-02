package domain.usecases;

import java.util.ArrayList;

import domain.entities.User;
import domain.repositories.IUsersRepository;

public class FindAllUsersUseCase {
    private IUsersRepository repository;

    public FindAllUsersUseCase(IUsersRepository repository) {
        this.repository = repository;
    } 

    public ArrayList<User> execute()  {
        return this.repository.findAll();
    } 
}
