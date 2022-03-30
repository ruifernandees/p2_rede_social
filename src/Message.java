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
}