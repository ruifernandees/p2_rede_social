package domain.repositories.implementations;

import domain.entities.FeedContent;
import domain.entities.Message;
import domain.repositories.IMessagesRepository;
import infra.singletons.DatabaseConnection;

public class MemoryMessagesRepository implements IMessagesRepository {

    DatabaseConnection connection;

    public MemoryMessagesRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public FeedContent findAll() {
        return this.connection.getMemoryDatabase().feed;
    }

    @Override
    public void addOnFeed(String content, String sender) {
        Message messageObject = new Message(content, sender);
        this.connection.getMemoryDatabase().feed.addMessage(messageObject);
    }

    public void removeAllMessagesFromUserByUsername(String username) {
        this.connection
            .getMemoryDatabase()
            .feed
            .messages
            .removeIf(message -> message.username.equals(username));
    }
}
