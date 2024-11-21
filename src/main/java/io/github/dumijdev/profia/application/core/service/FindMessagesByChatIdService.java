package io.github.dumijdev.profia.application.core.service;

import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.core.domain.Page;
import io.github.dumijdev.profia.application.ports.in.FindMessagesByChatIdInputPort;
import io.github.dumijdev.profia.application.ports.out.FindMessagesByChatIDOutputPort;

import java.util.List;

public class FindMessagesByChatIdService implements FindMessagesByChatIdInputPort {
    private final FindMessagesByChatIDOutputPort findMessagesByChatIDOutputPort;

    public FindMessagesByChatIdService(FindMessagesByChatIDOutputPort findMessagesByChatIDOutputPort) {
        this.findMessagesByChatIDOutputPort = findMessagesByChatIDOutputPort;
    }


    @Override
    public List<Message> findMessagesByChatId(String chatId) {
        return findMessagesByChatIDOutputPort.findMessagesByChatId(chatId);
    }
}
