package entities;
import java.util.ArrayList;

public class Conversation {
    private String firstUser;
    private String secondUser;
    private ArrayList<Message> messages;  

    public Conversation(String firstUser, String secondUser) {
        this.messages = new ArrayList<Message>();
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }

    public void addMessage(Message message) {
        if (message.username.equals(firstUser) || message.username.equals(secondUser)) {
            this.messages.add(message);
        }
    }

    public ArrayList<Message> getMessages() {
        return this.messages;
    }

    public void showAllMessages(String ownerUsername) {
        for (Message message : this.messages) {
            message.show(ownerUsername);
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (Message message : this.messages) {
            result += message.toString();
        }
        return result;
    }

    public String getFirstUser() {
        return firstUser;
    }

    public String getSecondUser() {
        return secondUser;
    }
}
