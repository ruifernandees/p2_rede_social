package domain.usecases;

import domain.dtos.FindByLoginDTO;
import domain.entities.User;
import domain.exceptions.LoginException;
import domain.repositories.IUsersRepository;
import domain.singletons.AuthenticationProvider;

public class LoginUseCase {
    private IUsersRepository repository;

    public LoginUseCase(IUsersRepository repository) {
        this.repository = repository;
    }

    public void execute(String login, String pwd) throws LoginException {
        FindByLoginDTO userDTO = this.repository.findByLogin(login);
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        try {
            User userToLogin = userDTO.user;
            Integer userIndex = userDTO.userIndex;
            if (userToLogin.pwd.equals(pwd)) {
                if (userToLogin.isActivated) {
                    authenticationProvider.setCurrentUser(userToLogin);
                    authenticationProvider.setCurrentUserIndex(userIndex);
                } else {
                    throw new LoginException("Usuário apagado!");
                }
            } else {
                throw new LoginException("Senha inválida!");
            } 
        } catch (NullPointerException e) {
            throw new LoginException("Usuário não encontrado. Por favor, crie uma conta.");
        } 
    }
}
