import java.util.ArrayList;

public class Feed {
    public ArrayList<Message> messages;
    
    public Feed() {
        this.messages = new ArrayList<Message>();
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void show(Integer viewerIndex, IUserDB db) {
        if (this.messages.size() == 0) {
            System.out.println("Sem conteúdo!\n");
            return;
        }
        for (int i = 0; i < this.messages.size(); i++) {
            for (User user : db.getAllUsers()) {
                if (user.username.equals(this.messages.get(i).username)) {
                    if (!user.feedMessageOnlyForFriends) {
                        System.out.println(this.messages.get(i));
                    } else if (user.friendsIndexes.contains(viewerIndex) || user.username.equals(db.getUser(viewerIndex).username)) {
                        System.out.println(this.messages.get(i));
                    }
                    break;
                }
            }
        }
        System.out.println();
    }
}