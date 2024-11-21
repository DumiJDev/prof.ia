package io.github.dumijdev.profia.application.ports.out;

import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.core.domain.Page;

import java.util.List;

public interface FindMessagesByChatIDOutputPort {
    List<Message> findMessagesByChatId(String chatId);
}
