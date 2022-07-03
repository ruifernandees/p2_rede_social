package presentation.cli.strategies;

import java.util.InputMismatchException;

import presentation.cli.CLIConstants;
import presentation.cli.CLIStrategy;
import presentation.controllers.EditProfileController;
import presentation.controllers.LogoutController;
import presentation.controllers.SendFriendRequestController;
import presentation.controllers.SendMessageToFeedController;
import presentation.controllers.SendMessageToInboxController;
import presentation.controllers.ShowAllUsersController;
import presentation.controllers.ShowFeedController;
import presentation.controllers.ShowFriendsRequestsController;
import presentation.controllers.ShowInboxController;
import presentation.controllers.ShowUserFriendsController;
import presentation.controllers.ViewProfileController;

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
            this.reader.nextLine();
            return CLIConstants.RUN_CLI;
        }
        switch (option) {
            case 1:
                EditProfileController.execute(this.reader);
                return CLIConstants.RUN_CLI;
            case 2:
                ViewProfileController.execute(this.reader);
                return CLIConstants.RUN_CLI;
            case 3:
                ShowFeedController.execute();
                return CLIConstants.RUN_CLI;
            case 4:
                SendMessageToFeedController.execute(this.reader);
                return CLIConstants.RUN_CLI;
            case 5:
                ShowAllUsersController.execute();
                return CLIConstants.RUN_CLI;
            case 6:
                SendFriendRequestController.execute(this.reader);
                return CLIConstants.RUN_CLI;
            case 7:
                ShowFriendsRequestsController.execute(this.reader);
                return CLIConstants.RUN_CLI;
            case 8:
                ShowUserFriendsController.execute();
                return CLIConstants.RUN_CLI;
            case 9:
                ShowInboxController.execute();
                return CLIConstants.RUN_CLI;
            case 10:
                SendMessageToInboxController.execute(this.reader);
                return CLIConstants.RUN_CLI;
            case 11:
            //     this.showCommunity();
            //     return CLIConstants.RUN_CLI;
            // case 12:
            //     this.enterCommunity();
            //     return CLIConstants.RUN_CLI;
            // case 13:
            //     this.createCommunity();
            //     return CLIConstants.RUN_CLI;
            case 100:
                LogoutController.execute();
                return CLIConstants.RUN_CLI;
            case 101:
                return CLIConstants.CLOSE_CLI;
            // case 102:
            //     this.removeUserInterface();
            //     return CLIConstants.RUN_CLI;
            default:
                System.out.println("Opção inválida!");
                this.reader.nextLine();
                return CLIConstants.RUN_CLI;
        } 
    }

}
