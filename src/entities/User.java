package entities;
import java.util.ArrayList;

public class User {
    public String username;
    public String login;
    public String pwd;
    public ArrayList<Integer> friendsIndexes;
    public ArrayList<Integer> requestingYourFriendshipIndexes;
    public Inbox inbox;
    private Boolean logged = false;
    public boolean isActivated = true;
    public boolean feedMessageOnlyForFriends = false;

    public User(
        String username, 
        String login, 
        String pwd, 
        ArrayList<Integer> friendsIndexes,
        ArrayList<Integer> requestingYourFriendshipIndexes
    ) {
        this.username = username;
        this.login = login;
        this.pwd = pwd;   
        this.friendsIndexes = friendsIndexes;
        this.requestingYourFriendshipIndexes = requestingYourFriendshipIndexes;
        this.inbox = new Inbox(this.username);
    }   

    @Override
    public String toString() {
        String result = "=> " + this.username;
        result += "\nLogin: " + this.login;
        if (this.logged) {
            result += "\nSua senha: " + this.pwd;
        }
        return result;
    }

    public Boolean isLogged() {
        return this.logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public void showInbox() {
        this.inbox.showAllMessages();
    }

    public void addMessageToInbox(Message message, String to) {
        this.inbox.addMessage(message, to);
    }
}