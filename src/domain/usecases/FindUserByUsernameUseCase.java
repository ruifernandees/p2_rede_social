package domain.usecases;

import domain.dtos.FindByUsernameDTO;
import domain.repositories.IUsersRepository;

public class FindUserByUsernameUseCase {
    private IUsersRepository repository;

    public FindUserByUsernameUseCase(IUsersRepository repository) {
        this.repository = repository;
    } 

    public FindByUsernameDTO execute(String username) {
        return this.repository.findByUsername(username);
    }
}
