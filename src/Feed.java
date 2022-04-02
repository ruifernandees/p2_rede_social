import java.util.ArrayList;

public class Feed {
    public ArrayList<Message> messages;
    
    public Feed() {
        this.messages = new ArrayList<Message>();
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void show() {
        if (this.messages.size() == 0) {
            System.out.println("Sem conteúdo!\n");
            return;
        }
        for (int i = 0; i < this.messages.size(); i++) {
            System.out.println(this.messages.get(i));
        }
        System.out.println();
    }
}