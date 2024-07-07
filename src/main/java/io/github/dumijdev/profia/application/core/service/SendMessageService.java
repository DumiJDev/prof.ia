package io.github.dumijdev.profia.application.core.service;

import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.ports.in.SendMessageInputPort;
import io.github.dumijdev.profia.application.ports.out.SaveMessageOuputPort;
import io.github.dumijdev.profia.application.ports.out.SendMessageOutputPort;

import java.util.Objects;

public class SendMessageService implements SendMessageInputPort {

    private final SendMessageOutputPort sendMessageOutputPort;

    public SendMessageService(SendMessageOutputPort sendMessageOutputPort) {
        this.sendMessageOutputPort = sendMessageOutputPort;
    }

    @Override
    public Message send(Message message) {
        validate(message);

        return sendMessageOutputPort.send(message);
    }

    void validate(Message message) {
        if (Objects.isNull(message)) {
            throw new IllegalArgumentException("Message is null");
        }

        if (Objects.isNull(message.content()) || message.content().isEmpty()) {
            throw new IllegalArgumentException("Message content is empty");
        }
    }
}
