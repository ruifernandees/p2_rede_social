package domain.usecases;

import domain.dtos.FindByUsernameDTO;
import domain.entities.Message;
import domain.entities.User;
import domain.repositories.IUsersRepository;
import domain.singletons.AuthenticationProvider;

public class SendMessageToInboxUseCase {
    private IUsersRepository repository;

    public SendMessageToInboxUseCase(IUsersRepository repository) {
        this.repository = repository;
    }  

    public void execute(Message message, String to) {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentLoggedUser = authenticationProvider.getCurrentUser();
        FindByUsernameDTO toUserDTO = this.repository.findByUsername(to);
        if (toUserDTO == null)  return;
        toUserDTO.user.inbox.addMessage(message, to);
        currentLoggedUser.inbox.addMessage(message, to);
        this.repository.update(toUserDTO.user);
        this.repository.update(currentLoggedUser);
    }
}
