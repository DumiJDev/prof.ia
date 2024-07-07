package io.github.dumijdev.profia.adapters.out.db;

import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.core.domain.Page;
import io.github.dumijdev.profia.application.ports.out.FindAllMessagesOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class FindAllMessagesMongoRepository implements FindAllMessagesOutputPort {
    private final MessageMongoRepository repository;

    @Override
    public List<Message> findAllMessages(Page page) {
        System.out.println(page);
        return repository.findAll(PageRequest.of(page.page(), page.size()))
                .map(message -> new Message(message.getId(), message.getContent(), Message.Sender.valueOf(message.getSender())))
                .toList();
    }
}
