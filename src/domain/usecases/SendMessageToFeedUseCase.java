package domain.usecases;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.exceptions.MessageException;
import domain.repositories.IMessagesRepository;

public class SendMessageToFeedUseCase {
    public IMessagesRepository messagesRepository;

    public SendMessageToFeedUseCase(IMessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }
    
    public void execute(String content, String sender) throws MessageException {
        ArrayList<String> blackList = new ArrayList<>();
        blackList.add("palavra proibida");
        blackList.add("palavra2");
        for (String word : blackList) {
            Pattern prohibitedWordPattern = Pattern.compile(word);
            Matcher wordMatcher = prohibitedWordPattern.matcher(content);
            boolean hasProhibitedWord = wordMatcher.find();
            if (hasProhibitedWord) {
                throw new MessageException("Mensagem não enviada!");
            }
        }
        this.messagesRepository.addOnFeed(content, sender);
    }
}
