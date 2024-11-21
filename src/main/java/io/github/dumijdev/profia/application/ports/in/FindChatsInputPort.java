package io.github.dumijdev.profia.application.ports.in;

import io.github.dumijdev.profia.application.core.domain.Chat;

import java.util.List;

public interface FindChatsInputPort {
    List<Chat> findChats();
}
