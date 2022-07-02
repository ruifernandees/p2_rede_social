package domain.usecases;

import java.util.ArrayList;

import domain.dtos.ShowFeedDTO;
import domain.entities.FeedContent;
import domain.entities.Message;
import domain.entities.User;
import domain.repositories.IMessagesRepository;
import domain.repositories.IUsersRepository;

public class ShowFeedUseCase {
    private IUsersRepository usersRepository;
    private IMessagesRepository messagesRepository;

    public ShowFeedUseCase(
        IUsersRepository usersRepository, 
        IMessagesRepository messagesRepository
    ) {
        this.usersRepository = usersRepository;
        this.messagesRepository = messagesRepository;
    }    

    public FeedContent execute(ShowFeedDTO params) {
        ArrayList<User> allUsers = this.usersRepository.findAll();
        FeedContent feed = this.messagesRepository.findAll();
        FeedContent restrictedFeed = new FeedContent();
        for (Message message : feed.messages) {
            for (User user : allUsers) {
                if (user.username.equals(message.username)) {
                    if (!user.feedMessageOnlyForFriends) {
                        restrictedFeed.addMessage(message);
                    } else if (
                        user.friendsIndexes.contains(params.userIndex) 
                        || user.username.equals(params.user.username) 
                    ){
                        restrictedFeed.addMessage(message);
                    }
                    break;
                }              
            }           
        }
        return restrictedFeed;
    }
}
