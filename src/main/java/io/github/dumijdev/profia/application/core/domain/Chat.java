package io.github.dumijdev.profia.application.core.domain;

import java.util.List;

public record Chat(Long id, String name, List<Message> messages) {
}
