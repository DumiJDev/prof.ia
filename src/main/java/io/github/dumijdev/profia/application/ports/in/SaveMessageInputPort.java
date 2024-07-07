package io.github.dumijdev.profia.application.ports.in;

import io.github.dumijdev.profia.application.core.domain.Message;

public interface SaveMessageInputPort {
    Message save(Message message);
}
