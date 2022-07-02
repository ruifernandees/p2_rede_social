package domain.entities;
public class Message {
    public String content;
    public String username;

    public Message(String content, String username) {
        this.content = content;
        this.username = username;
    }

    @Override
    public String toString() {
        return "\n" + this.username + ": " + this.content;
    }

    public void show(String ownerUsername) {
        String sender = this.username;
        if (sender.equals(ownerUsername)) {
            sender = "Você";
        }
        System.out.println(sender + ": " + this.content);
        
    }
}