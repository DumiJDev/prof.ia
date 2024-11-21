package io.github.dumijdev.profia.application.ports.out;

import io.github.dumijdev.profia.application.core.domain.Chat;

public interface FindChatByIdOutputPort {
    Chat findChatById(String id);
}
