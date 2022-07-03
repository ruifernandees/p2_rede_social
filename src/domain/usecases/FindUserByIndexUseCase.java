package domain.usecases;

import domain.entities.User;
import domain.repositories.IUsersRepository;

public class FindUserByIndexUseCase {
    private IUsersRepository repository;

    public FindUserByIndexUseCase(IUsersRepository repository) {
        this.repository = repository;
    } 

    public User execute(Integer index) {
        return this.repository.findByIndex(index);
    }
}
