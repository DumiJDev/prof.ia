package io.github.dumijdev.profia.adapters.out.db;

import io.github.dumijdev.profia.adapters.out.db.models.MessageModel;
import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.ports.out.SaveMessageOuputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveMongoRepository implements SaveMessageOuputPort {
    private final MessageMongoRepository repository;

    @Override
    public Message saveMessage(Message message) {
        var messageDocument = new MessageModel();

        messageDocument.setSender(message.sender().name());
        messageDocument.setContent(message.content());
        messageDocument.setCreatedAt(message.datetime());

        var saved = repository.save(messageDocument);

        System.out.println("Saved message: " + saved);

        return message;
    }
}
