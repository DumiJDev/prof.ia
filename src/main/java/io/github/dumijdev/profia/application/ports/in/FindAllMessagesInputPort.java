package io.github.dumijdev.profia.application.ports.in;

import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.core.domain.Page;

import java.util.List;

public interface FindAllMessagesInputPort {
    List<Message> findAllMessages(Page page);
}
