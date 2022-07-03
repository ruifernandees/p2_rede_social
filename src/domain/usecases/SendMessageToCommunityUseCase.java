package domain.usecases;

import domain.entities.Message;
import domain.repositories.ICommunitiesRepository;

public class SendMessageToCommunityUseCase {
    private ICommunitiesRepository repository;

    public SendMessageToCommunityUseCase(ICommunitiesRepository repository) {
        this.repository = repository;
    }   

    public void execute(Message message, Integer communityIndex) {
        this.repository.addMessageInCommunity(message, communityIndex);
    }
}
