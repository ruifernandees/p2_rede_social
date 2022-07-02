package domain.singletons;

import domain.entities.User;

public class AuthenticationProvider {
    private static AuthenticationProvider instance;

    private User currentUser;
    private int currentUserIndex;

    private AuthenticationProvider() {}

    public static AuthenticationProvider getInstance() {
        if (AuthenticationProvider.instance == null) {
            AuthenticationProvider.instance = new AuthenticationProvider();
        }
        return AuthenticationProvider.instance;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUserIndex(int index) {
        this.currentUserIndex = index;
    }

    public int getCurrentUserIndex() {
        return this.currentUserIndex; 
    }
}
