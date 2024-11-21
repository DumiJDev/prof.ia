package io.github.dumijdev.profia.adapters.output.db.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageModel {
    private String id;
    private String content;
    private LocalDateTime createdAt;
    private String sender;
}
