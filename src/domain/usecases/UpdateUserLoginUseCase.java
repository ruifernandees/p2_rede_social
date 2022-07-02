package domain.usecases;

import domain.dtos.FindByLoginDTO;
import domain.entities.User;
import domain.repositories.IUsersRepository;
import domain.singletons.AuthenticationProvider;
import utils.ProfileMatcher;

public class UpdateUserLoginUseCase {
    private IUsersRepository repository;

    public UpdateUserLoginUseCase(IUsersRepository repository) {
        this.repository = repository;
    } 

    public void execute(String newLogin) throws IllegalArgumentException {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        Boolean hasWhitespace = ProfileMatcher.hasWhitespace(newLogin);
        Boolean hasSomeAlphanumericCharInLogin = ProfileMatcher.hasSomeAlphanumericChar(newLogin);
        if (hasWhitespace || !hasSomeAlphanumericCharInLogin) {
            throw new IllegalArgumentException("Erro! Login possui espaços em branco ou caracteres inválidos!");
        }
        FindByLoginDTO userWithTheSameLogin = this.repository.findByLogin(newLogin);
        if (userWithTheSameLogin != null) {
            throw new IllegalArgumentException("Erro! Já existe um usuário com esse login!");
        }
        User currentUser = authenticationProvider.getCurrentUser();
        currentUser.login = newLogin;
        authenticationProvider.setCurrentUser(currentUser);
        this.repository.update(currentUser);
    } 
}
