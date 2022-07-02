package domain.usecases;

import java.util.ArrayList;

import domain.dtos.CreateUserDTO;
import domain.dtos.FindByLoginDTO;
import domain.entities.User;
import domain.exceptions.SignUpException;
import domain.repositories.IUsersRepository;
import utils.ProfileMatcher;

public class SignUpUseCase {
    private IUsersRepository repository;

    public SignUpUseCase(IUsersRepository repository) {
        this.repository = repository;
    }

    public void execute(CreateUserDTO params) throws SignUpException {
        Boolean hasNonAlphaChar = ProfileMatcher.hasNonAlphaChar(params.username);
        Boolean hasSomeAlphaChar = ProfileMatcher.hasSomeAlphaChar(params.username);
        if (hasNonAlphaChar || !hasSomeAlphaChar) {
            throw new SignUpException("Nome de usuário possui caracteres inválidos!");
        }
        Boolean hasWhitespace = ProfileMatcher.hasWhitespace(params.login);
        Boolean hasSomeAlphanumericCharInLogin = ProfileMatcher.hasSomeAlphanumericChar(params.login);
        if (hasWhitespace || !hasSomeAlphanumericCharInLogin) {
            throw new SignUpException("Login possui espaços em branco ou caracteres inválidos!");
        }
        FindByLoginDTO userDTO = this.repository.findByLogin(params.login);
        if (userDTO != null) {
            throw new SignUpException("Login já cadastrado em outra conta!");
        } 
        boolean hasWhitespaceInPwd = ProfileMatcher.hasWhitespace(params.password);
        Boolean hasSomeAlphanumericCharOnPwd = ProfileMatcher.hasSomeAlphanumericChar(params.password);
        if (hasWhitespaceInPwd || !hasSomeAlphanumericCharOnPwd) {
            throw new SignUpException("Senha não deve possuir espaços em branco e deve ter conteúdo!");
        }
        User user = new User(params.username, params.login, params.password, new ArrayList<Integer>(), new ArrayList<Integer>());
        this.repository.create(user);
    }
}
