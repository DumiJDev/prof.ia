package io.github.dumijdev.profia.adapters.output.db.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Data
@Document(collection = "chats")
public class ChatModel {
    @Id
    @Indexed(unique = true)
    @MongoId(FieldType.STRING)
    private String id;
    private String title;
    private List<MessageModel> messages = new LinkedList<>();
    private LocalDateTime createdAt;

    public ChatModel(String id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
        this.title = id;
    }

    public ChatModel() {
        this.createdAt = LocalDateTime.now();
        this.title = randomUUID().toString();
    }
}
