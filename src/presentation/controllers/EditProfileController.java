package presentation.controllers;

import java.util.Scanner;

import domain.repositories.implementations.MemoryUsersRepository;
import domain.usecases.UpdateUserFeedMessageVisibleOption;
import domain.usecases.UpdateUserLoginUseCase;
import domain.usecases.UpdateUserNameUseCase;
import domain.usecases.UpdateUserPasswordUseCase;

public class EditProfileController {
    public static void execute(Scanner reader) {
        System.out.println("Edição perfil:");
        System.out.println("Novo nome [digite \"N\" caso queira manter o mesmo]");
        reader.nextLine();
        String newName = reader.nextLine();
        MemoryUsersRepository usersRepository = new MemoryUsersRepository();
        try {
            if (!newName.equals("N")) {
                UpdateUserNameUseCase updateUserNameUseCase = new UpdateUserNameUseCase(usersRepository);
                updateUserNameUseCase.execute(newName);
            }
            System.out.println("Novo login [digite \"N\" caso queira manter o mesmo]");
            String newLogin = reader.nextLine();
            if (!newLogin.equals("N")) {
                UpdateUserLoginUseCase updateUserLoginUseCase = new UpdateUserLoginUseCase(usersRepository);
                updateUserLoginUseCase.execute(newLogin);
            }
            System.out.println("Nova senha [digite \"N\" caso queira manter a mesma senha]");
            String newPwd = reader.nextLine();
            if (!newPwd.equals("N")) {
                UpdateUserPasswordUseCase updateUserPasswordUseCase = new UpdateUserPasswordUseCase(usersRepository);
                updateUserPasswordUseCase.execute(newPwd);
            }    
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Mostrar mensagens do feed apenas para amigos? [S/N]");
        String result = reader.next();
        UpdateUserFeedMessageVisibleOption updateUserFeedMessageVisibleOption = new UpdateUserFeedMessageVisibleOption(usersRepository);
        if (result.equals("S")) {
            updateUserFeedMessageVisibleOption.execute(true);
        } else if (result.equals("N")) {
            updateUserFeedMessageVisibleOption.execute(false);
        } else {
            System.out.println("Opção inválida!");
        }
        System.out.println("Dados atualizados com sucesso!");
    }
}
