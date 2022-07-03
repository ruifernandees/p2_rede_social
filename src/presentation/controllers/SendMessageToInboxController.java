package presentation.controllers;

import java.util.Scanner;

import domain.dtos.FindByUsernameDTO;
import domain.entities.Message;
import domain.entities.User;
import domain.repositories.implementations.MemoryUsersRepository;
import domain.singletons.AuthenticationProvider;
import domain.usecases.FindUserByUsernameUseCase;
import domain.usecases.SendMessageToInboxUseCase;

public class SendMessageToInboxController {
    public static void execute(Scanner reader) {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentLoggedUser = authenticationProvider.getCurrentUser();
        System.out.println("=> Mensagem para (username): ");
        String to = reader.next();
        reader.nextLine();
        MemoryUsersRepository memoryUsersRepository = new MemoryUsersRepository();
        FindUserByUsernameUseCase findUserByUsernameUseCase = new FindUserByUsernameUseCase(
            memoryUsersRepository 
        );
        FindByUsernameDTO userDTO = findUserByUsernameUseCase.execute(to);
        if (userDTO == null) {
            System.out.println("Erro! Usuário não existe!");
            return;
        }
        System.out.print("=> Conteúdo: ");
        String content = reader.nextLine();
        Message message = new Message(content, currentLoggedUser.username);
        SendMessageToInboxUseCase sendMessageToInboxUseCase = new SendMessageToInboxUseCase(memoryUsersRepository);
        sendMessageToInboxUseCase.execute(message, to);
        System.out.println("Mensagem enviada para " + to + "!");
    }
}
