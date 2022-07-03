package domain.usecases;

import domain.dtos.AcceptFriendRequestUseCaseDTO;
import domain.entities.User;
import domain.repositories.IUsersRepository;
import domain.singletons.AuthenticationProvider;

public class AcceptFriendRequestUseCase {
    private IUsersRepository repository;

    public AcceptFriendRequestUseCase(IUsersRepository repository) {
        this.repository = repository;
    }  

    public void execute(AcceptFriendRequestUseCaseDTO params) {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentLoggedUser = authenticationProvider.getCurrentUser();
        Integer currentLoggedUserIndex= authenticationProvider.getCurrentUserIndex();
        currentLoggedUser.friendsIndexes.add(params.userIndex);
        params.user.friendsIndexes.add(currentLoggedUserIndex);
        this.repository.update(params.user);
        this.repository.update(currentLoggedUser);
    }
}
