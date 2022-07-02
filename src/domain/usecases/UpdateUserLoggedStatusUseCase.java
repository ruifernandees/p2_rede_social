package domain.usecases;

import domain.entities.User;
import domain.repositories.IUsersRepository;
import domain.singletons.AuthenticationProvider;

public class UpdateUserLoggedStatusUseCase {
    private IUsersRepository repository;

    public UpdateUserLoggedStatusUseCase(IUsersRepository repository) {
        this.repository = repository;
    } 

    public void execute(Boolean logged) throws IllegalArgumentException {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentUser = authenticationProvider.getCurrentUser();
        currentUser.setLogged(logged);
        this.repository.update(currentUser);
        if (logged) {
            authenticationProvider.setCurrentUser(currentUser);
        } else {
            authenticationProvider.setCurrentUser(null);
            authenticationProvider.setCurrentUserIndex(-1);
        }
    } 
}
