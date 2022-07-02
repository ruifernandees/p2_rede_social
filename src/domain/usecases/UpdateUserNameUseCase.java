package domain.usecases;

import domain.entities.User;
import domain.repositories.IUsersRepository;
import domain.singletons.AuthenticationProvider;
import utils.ProfileMatcher;

public class UpdateUserNameUseCase {
    private IUsersRepository repository;

    public UpdateUserNameUseCase(IUsersRepository repository) {
        this.repository = repository;
    } 

    public void execute(String newName) throws IllegalArgumentException {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        Boolean hasNonAlphaChar = ProfileMatcher.hasNonAlphaChar(newName);
        Boolean hasSomeAlphaChar = ProfileMatcher.hasSomeAlphaChar(newName);
        if (hasNonAlphaChar || !hasSomeAlphaChar) {
            throw new IllegalArgumentException("Nome de usuário possui caracteres inválidos!");
        }
        User currentUser = authenticationProvider.getCurrentUser();
        currentUser.username = newName;
        authenticationProvider.setCurrentUser(currentUser);
        this.repository.update(currentUser);
    }
}
