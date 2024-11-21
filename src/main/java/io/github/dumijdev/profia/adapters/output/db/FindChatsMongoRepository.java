package io.github.dumijdev.profia.adapters.output.db;

import io.github.dumijdev.profia.adapters.output.db.models.ChatModel;
import io.github.dumijdev.profia.adapters.output.db.models.MessageModel;
import io.github.dumijdev.profia.application.core.domain.Chat;
import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.ports.out.FindChatsOutputPort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FindChatsMongoRepository implements FindChatsOutputPort {
    private final ChatMongoRepository chatMongoRepository;

    public FindChatsMongoRepository(ChatMongoRepository chatMongoRepository) {
        this.chatMongoRepository = chatMongoRepository;
    }

    @Override
    public List<Chat> findChats() {
        return chatMongoRepository.findAll()
                .stream()
                .map(this::mapper)
                .toList();
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
