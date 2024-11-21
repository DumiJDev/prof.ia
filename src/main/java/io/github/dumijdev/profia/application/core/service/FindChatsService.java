package io.github.dumijdev.profia.application.core.service;

import io.github.dumijdev.profia.application.core.domain.Chat;
import io.github.dumijdev.profia.application.ports.in.FindChatsInputPort;
import io.github.dumijdev.profia.application.ports.out.FindChatsOutputPort;

import java.util.List;

public class FindChatsService implements FindChatsInputPort {
    private final FindChatsOutputPort findChats;

    public FindChatsService(FindChatsOutputPort findChats) {
        this.findChats = findChats;
    }

    @Override
    public List<Chat> findChats() {
        return findChats.findChats();
    }
}
