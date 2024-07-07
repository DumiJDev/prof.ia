package io.github.dumijdev.profia.application.ports.out;

import io.github.dumijdev.profia.application.core.domain.Message;

public interface SaveMessageOuputPort {
    Message saveMessage(Message message);
}
