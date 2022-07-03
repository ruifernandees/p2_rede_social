package presentation.cli.strategies;

import java.util.ArrayList;
import java.util.InputMismatchException;

import domain.dtos.GetPossibleUsersToSendRequestByUserLoginDTO;
import domain.dtos.SendFriendRequestToAUserUseCaseDTO;
import domain.dtos.ShowFeedDTO;
import domain.entities.Community;
import domain.entities.FeedContent;
import domain.entities.Message;
import domain.entities.User;
import domain.repositories.implementations.MemoryCommunitiesRepository;
import domain.repositories.implementations.MemoryMessagesRepository;
import domain.repositories.implementations.MemoryUsersRepository;
import domain.singletons.AuthenticationProvider;
import domain.usecases.FindAllUsersUseCase;
import domain.usecases.GetPossibleUsersToSendRequestByUserLogin;
import domain.usecases.GetUserCommunitiesUseCase;
import domain.usecases.SendFriendRequestToAUserUseCase;
import domain.usecases.SendMessageToFeedUseCase;
import domain.usecases.ShowFeedUseCase;
import domain.usecases.UpdateUserFeedMessageVisibleOption;
import domain.usecases.UpdateUserLoggedStatusUseCase;
import domain.usecases.UpdateUserLoginUseCase;
import domain.usecases.UpdateUserNameUseCase;
import domain.usecases.UpdateUserPasswordUseCase;
import presentation.cli.CLIConstants;
import presentation.cli.CLIStrategy;

public class AppCLIStrategy extends CLIStrategy {
    public AppCLIStrategy() {
        super();
    }

    @Override
    public boolean execute() {
        System.out.println("[1] Editar Perfil");
        System.out.println("[2] Mostrar Perfil");
        System.out.println("[3] Abrir feed");
        System.out.println("[4] Enviar mensagem no feed");
        System.out.println("[5] Todos usuários da rede");
        System.out.println("[6] Enviar solicitação de amizade (interativo)");
        System.out.println("[7] Ver solicitações de amizade (interativo)");
        System.out.println("[8] Ver amigos");
        System.out.println("[9] Ver inbox");
        System.out.println("[10] Enviar mensagem para alguém");
        System.out.println("[11] Mandar mensagem para comunidades");
        System.out.println("[12] Entrar em comunidades");
        System.out.println("[13] Criar comunidade");
        System.out.println("[100] Encerrar sessão");
        System.out.println("[101] Sair do IFace");
        System.out.println("[102] Remover a conta");
        System.out.print("=> ");
        int option = -1;
        try {
            option = this.reader.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Opção inválida!");
            return CLIConstants.RUN_CLI;
        }
        switch (option) {
            case 1:
                this.editProfile();
                return CLIConstants.RUN_CLI;
            case 2:
                this.viewProfile();
                return CLIConstants.RUN_CLI;
            case 3:
                this.showFeed();
                return CLIConstants.RUN_CLI;
            case 4:
                this.sendMessageToFeed();
                return CLIConstants.RUN_CLI;
            case 5:
                this.showAllUsers();
                return CLIConstants.RUN_CLI;
            case 6:
                this.sendFriendRequest();
                return CLIConstants.RUN_CLI;
            // case 7:
            //     this.showFriendsRequests();
            //     return CLIConstants.RUN_CLI;
            // case 8:
            //     this.showFriends();
            //     return CLIConstants.RUN_CLI;
            // case 9:
            //     this.showInbox();
            //     return CLIConstants.RUN_CLI;
            // case 10:
            //     this.sendMessageToInbox();
            //     return CLIConstants.RUN_CLI;
            // case 11:
            //     this.showCommunity();
            //     return CLIConstants.RUN_CLI;
            // case 12:
            //     this.enterCommunity();
            //     return CLIConstants.RUN_CLI;
            // case 13:
            //     this.createCommunity();
            //     return CLIConstants.RUN_CLI;
            case 100:
                this.logout();
                return CLIConstants.RUN_CLI;
            case 101:
                return CLIConstants.CLOSE_CLI;
            // case 102:
            //     this.removeUserInterface();
            //     return CLIConstants.RUN_CLI;
            default:
                System.out.println("Opção inválida!");
                return CLIConstants.RUN_CLI;
        } 
    }

    private void editProfile() {
        System.out.println("Edição perfil:");
        System.out.println("Novo nome [digite \"N\" caso queira manter o mesmo]");
        this.reader.nextLine();
        String newName = this.reader.nextLine();
        MemoryUsersRepository usersRepository = new MemoryUsersRepository();
        try {
            if (!newName.equals("N")) {
                UpdateUserNameUseCase updateUserNameUseCase = new UpdateUserNameUseCase(usersRepository);
                updateUserNameUseCase.execute(newName);
            }
            System.out.println("Novo login [digite \"N\" caso queira manter o mesmo]");
            String newLogin = this.reader.nextLine();
            if (!newLogin.equals("N")) {
                UpdateUserLoginUseCase updateUserLoginUseCase = new UpdateUserLoginUseCase(usersRepository);
                updateUserLoginUseCase.execute(newLogin);
            }
            System.out.println("Nova senha [digite \"N\" caso queira manter a mesma senha]");
            String newPwd = this.reader.nextLine();
            if (!newPwd.equals("N")) {
                UpdateUserPasswordUseCase updateUserPasswordUseCase = new UpdateUserPasswordUseCase(usersRepository);
                updateUserPasswordUseCase.execute(newPwd);
            }    
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Mostrar mensagens do feed apenas para amigos? [S/N]");
        String result = this.reader.next();
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

    public void viewProfile() {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentUser = authenticationProvider.getCurrentUser();
        System.out.println("\n" + currentUser + "\n");
        System.out.println("==== SUAS COMUNIDADES ====");
        GetUserCommunitiesUseCase getUserCommunitiesUseCase = new GetUserCommunitiesUseCase(
            new MemoryCommunitiesRepository()
        );
        ArrayList<Community> communities = getUserCommunitiesUseCase.execute(currentUser.username);
        for (Community community : communities) {
            System.out.println(community.name);
            System.out.println("  => " + community.description); 
        }
        System.out.println();
    }

    public void showFeed() {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        ShowFeedUseCase showFeedUseCase = new ShowFeedUseCase(
            new MemoryUsersRepository(), 
            new MemoryMessagesRepository() 
        );
        User currentUser = authenticationProvider.getCurrentUser();
        Integer currentUserIndex = authenticationProvider.getCurrentUserIndex();
        FeedContent restrictedFeed = showFeedUseCase.execute(new ShowFeedDTO(currentUser, currentUserIndex));
        System.out.println("=== Feed ===");
        for (Message message : restrictedFeed.messages) {
            System.out.println(message);
        }
    }

    public void sendMessageToFeed() {
        this.reader.nextLine();
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentUser = authenticationProvider.getCurrentUser();
        System.out.println("Nova mensagem: ");
        String message = this.reader.nextLine();
        try {
            SendMessageToFeedUseCase sendMessageToFeedUseCase = new SendMessageToFeedUseCase(new MemoryMessagesRepository()); 
            sendMessageToFeedUseCase.execute(message, currentUser.username);
            System.out.println(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        
    }

    public void logout() {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentUser = authenticationProvider.getCurrentUser();
        System.out.println("Até mais, " + currentUser.username + "!");
        UpdateUserLoggedStatusUseCase updateUserLoggedStatusUseCase = new UpdateUserLoggedStatusUseCase(new MemoryUsersRepository());
        updateUserLoggedStatusUseCase.execute(false);
    }

    public void showAllUsers() {
        FindAllUsersUseCase findAllUsersUseCase = new FindAllUsersUseCase(new MemoryUsersRepository());
        ArrayList<User> allUsers = findAllUsersUseCase.execute();
        for (User user : allUsers) {
            System.out.println(user); 
        }
    }

    public void sendFriendRequest() {
        MemoryUsersRepository memoryUsersRepository = new MemoryUsersRepository();
        GetPossibleUsersToSendRequestByUserLogin getPossibleUsersToSendRequestByUserLogin = new GetPossibleUsersToSendRequestByUserLogin(
            memoryUsersRepository 
        );
        ArrayList<GetPossibleUsersToSendRequestByUserLoginDTO> usersRequestingYou = getPossibleUsersToSendRequestByUserLogin.execute();
        Integer counter = 1;
        for (GetPossibleUsersToSendRequestByUserLoginDTO currentUserDTO : usersRequestingYou) {
            User currentUser = currentUserDTO.user;
            System.out.println(counter + ". " + currentUser.username + ". Adicionar [S/N]");
            counter++;
            String response = this.reader.next();
            if (response.equals("S")) {
                SendFriendRequestToAUserUseCase sendFriendRequestToAUserUseCase = new SendFriendRequestToAUserUseCase(memoryUsersRepository);
                try {
                    sendFriendRequestToAUserUseCase.execute(new SendFriendRequestToAUserUseCaseDTO(currentUser, currentUserDTO.userIndex));
                    System.out.println("Pedido feito a " + currentUser.username + " com sucesso!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (response.equals("N")) {
                continue;
            } else {
                System.out.println("Resposta inválida!");
            }
        }

    }
}
