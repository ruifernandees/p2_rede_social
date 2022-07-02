package domain.usecases;

import domain.entities.User;
import domain.repositories.IUsersRepository;
import domain.singletons.AuthenticationProvider;
import utils.ProfileMatcher;

public class UpdateUserPasswordUseCase {
    private IUsersRepository repository;

    public UpdateUserPasswordUseCase(IUsersRepository repository) {
        this.repository = repository;
    } 

    public void execute(String newPassword) throws IllegalArgumentException {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        boolean hasWhitespaceInPwd = ProfileMatcher.hasWhitespace(newPassword);
        Boolean hasSomeAlphanumericCharOnPwd = ProfileMatcher.hasSomeAlphanumericChar(newPassword);
        if (hasWhitespaceInPwd || !hasSomeAlphanumericCharOnPwd) {
            throw new IllegalArgumentException("Erro! Senha não deve possuir espaços em branco e deve ter conteúdo!");
        }
        User currentUser = authenticationProvider.getCurrentUser();
        currentUser.pwd = newPassword;
        authenticationProvider.setCurrentUser(currentUser);
        this.repository.update(currentUser);
    } 
}
