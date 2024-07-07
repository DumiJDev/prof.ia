package io.github.dumijdev.profia.application.ports.out;

import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.core.domain.Page;

import java.util.List;

public interface FindAllMessagesOutputPort {
    List<Message> findAllMessages(Page page);
}
