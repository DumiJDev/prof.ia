package io.github.dumijdev.profia.application.core.domain;

import java.util.List;

public record Chat(String id, String title, List<Message> messages) {
}
