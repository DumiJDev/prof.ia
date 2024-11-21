package io.github.dumijdev.profia.application.ports.out;

import io.github.dumijdev.profia.application.core.domain.Chat;

import java.util.List;

public interface FindChatsOutputPort {
    List<Chat> findChats();
}
