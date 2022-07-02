package domain.repositories;

import domain.entities.FeedContent;

public interface IMessagesRepository {
    public FeedContent findAll();
    public void addOnFeed(String content, String sender);
}
