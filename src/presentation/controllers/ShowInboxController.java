package presentation.controllers;

import domain.singletons.AuthenticationProvider;

public class ShowInboxController {
    public static void execute() {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        authenticationProvider.getCurrentUser().inbox.showAllMessages();
    }
}
