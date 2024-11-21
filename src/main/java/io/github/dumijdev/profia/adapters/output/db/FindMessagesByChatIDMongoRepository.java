package io.github.dumijdev.profia.adapters.output.db;

import io.github.dumijdev.profia.adapters.output.db.models.ChatModel;
import io.github.dumijdev.profia.adapters.output.db.models.MessageModel;
import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.core.domain.Page;
import io.github.dumijdev.profia.application.ports.out.FindMessagesByChatIDOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.github.dumijdev.profia.application.core.domain.Message.Sender.valueOf;
import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Repository
public class FindMessagesByChatIDMongoRepository implements FindMessagesByChatIDOutputPort {
    private final ChatMongoRepository repository;



    @Override
    public List<Message> findMessagesByChatId(String chatId) {
        return repository.findById(chatId).orElse(new ChatModel())
                .getMessages()
                .stream()
                .map(messageModel -> mapper(chatId, messageModel))
                .toList();
    }

    private Message mapper(String chatId, MessageModel message) {
        return new Message(chatId, message.getContent(), valueOf(message.getSender()));
    }
}
