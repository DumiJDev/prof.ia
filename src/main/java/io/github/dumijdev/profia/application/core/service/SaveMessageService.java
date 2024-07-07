package io.github.dumijdev.profia.application.core.service;

import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.ports.in.SaveMessageInputPort;
import io.github.dumijdev.profia.application.ports.out.SaveMessageOuputPort;

public class SaveMessageService implements SaveMessageInputPort {
    private final SaveMessageOuputPort saveMessageOuputPort;

    public SaveMessageService(SaveMessageOuputPort saveMessageOuputPort) {
        this.saveMessageOuputPort = saveMessageOuputPort;
    }

    @Override
    public Message save(Message message) {
        saveMessageOuputPort.saveMessage(message);

        return message;
    }
}
