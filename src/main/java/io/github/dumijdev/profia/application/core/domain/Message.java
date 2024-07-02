package io.github.dumijdev.profia.application.core.domain;

import java.time.LocalDateTime;

public record Message(String chatId, String content, Sender sender, LocalDateTime datetime) {
    public Message(String chatId, String content) {
        this(chatId, content, Sender.USER, LocalDateTime.now());
    }

    public Message(String chatId, String content, Sender sender) {
        this(chatId, content, sender, LocalDateTime.now());
    }

    public enum Sender {
        USER, IA
    }
}
