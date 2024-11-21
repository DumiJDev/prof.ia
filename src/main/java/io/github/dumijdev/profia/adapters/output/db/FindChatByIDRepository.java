package io.github.dumijdev.profia.adapters.output.db;

import io.github.dumijdev.profia.adapters.output.db.models.ChatModel;
import io.github.dumijdev.profia.adapters.output.db.models.MessageModel;
import io.github.dumijdev.profia.application.core.domain.Chat;
import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.ports.out.FindChatByIdOutputPort;
import org.springframework.stereotype.Repository;

@Repository
public class FindChatByIDRepository implements FindChatByIdOutputPort {
    private final ChatMongoRepository chatMongoRepository;

    public FindChatByIDRepository(ChatMongoRepository chatMongoRepository) {
        this.chatMongoRepository = chatMongoRepository;
    }

    @Override
    public Chat findChatById(String id) {
        var chat = chatMongoRepository.findById(id).orElse(new ChatModel());

        return mapper(chat);
    }

    private Chat mapper(ChatModel chat) {
        return new Chat(
                chat.getId(),
                chat.getTitle(),
                chat.getMessages()
                        .stream()
                        .map(messageModel -> mapMessage(chat.getId(), messageModel))
                        .toList()
        );
    }

    private Message mapMessage(String chatId, MessageModel messageModel) {
        return new Message(
                chatId,
                messageModel.getContent(),
                Message.Sender.valueOf(messageModel.getSender()),
                messageModel.getCreatedAt()
        );
    }
}
