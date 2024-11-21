package io.github.dumijdev.profia.adapters.output.db;

import io.github.dumijdev.profia.adapters.output.db.models.ChatModel;
import io.github.dumijdev.profia.adapters.output.db.models.MessageModel;
import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.ports.out.SaveMessageOuputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SaveMongoRepository implements SaveMessageOuputPort {
    private final ChatMongoRepository chatMongoRepository;

    @Override
    public Message saveMessage(String chatId, Message message) {
        var chat = chatMongoRepository.findById(chatId).orElse(new ChatModel(chatId));

        chat.getMessages().add(mapMessage(message));

        chatMongoRepository.save(chat);

        return message;
    }

    private MessageModel mapMessage(Message message) {
        var model = new MessageModel();

        model.setId(UUID.randomUUID().toString());
        model.setSender(message.sender().name());
        model.setCreatedAt(message.datetime());
        model.setContent(message.content());

        return model;
    }

}
