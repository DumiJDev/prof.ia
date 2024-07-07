package io.github.dumijdev.profia.adapters.out.db.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@Document(collection = "messages")
public class MessageModel {
    @Id
    @MongoId(FieldType.OBJECT_ID)
    @Indexed(unique = true)
    private String id;
    private String content;
    private LocalDateTime createdAt;
    @Indexed(unique = true)
    private String sender;
}
