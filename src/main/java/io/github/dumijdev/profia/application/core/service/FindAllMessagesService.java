package io.github.dumijdev.profia.application.core.service;

import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.core.domain.Page;
import io.github.dumijdev.profia.application.ports.in.FindAllMessagesInputPort;
import io.github.dumijdev.profia.application.ports.out.FindAllMessagesOutputPort;

import java.util.List;

public class FindAllMessagesService implements FindAllMessagesInputPort {
    private final FindAllMessagesOutputPort findAllMessagesOutputPort;

    public FindAllMessagesService(FindAllMessagesOutputPort findAllMessagesOutputPort) {
        this.findAllMessagesOutputPort = findAllMessagesOutputPort;
    }

    @Override
    public List<Message> findAllMessages(Page page) {
        return findAllMessagesOutputPort.findAllMessages(page);
    }
}
