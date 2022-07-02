package entities;
import java.util.ArrayList;

public class Inbox {
    public ArrayList<Conversation> conversations;
    private String ownerUsername;

    public Inbox(String ownerUsername) {
        this.conversations = new ArrayList<Conversation>();
        this.ownerUsername = ownerUsername;
    }

    public void addMessage(Message message, String to) {
        for (Conversation conversation : this.conversations) {
            if (
                (conversation.getFirstUser().equals(message.username) || conversation.getSecondUser().equals(message.username))
                || (conversation.getFirstUser().equals(to) || conversation.getSecondUser().equals(to))
            ) {
                conversation.addMessage(message);
                return;
            }
        }
        Conversation newConversation = new Conversation(to, message.username);
        newConversation.addMessage(message);
        this.addConversation(newConversation);
    }

    private void addConversation(Conversation conversation) {
        this.conversations.add(conversation);
    }

    public void showAllMessages() {
        System.out.println("==== INBOX ====");
        for (Conversation conversation : this.conversations) {
            String another;
            if (conversation.getFirstUser().equals(this.ownerUsername)) {
                another = conversation.getSecondUser();
            } else {
                another = conversation.getFirstUser();
            }
            System.out.println("## Conversa com " + another);
            conversation.showAllMessages(this.ownerUsername);
            System.out.println();
        }
    }
}
