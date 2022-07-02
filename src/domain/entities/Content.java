package domain.entities;
import java.util.ArrayList;

public abstract class Content {
  public ArrayList<Message> messages;

  public Content() {
    this.messages = new ArrayList<Message>();
  }

  public void addMessage(Message message) {
    this.messages.add(message);
  }

  public void show() {
    for (int i = 0; i < this.messages.size(); i++) {
      System.out.println(this.messages.get(i));
    } 
  }
}
