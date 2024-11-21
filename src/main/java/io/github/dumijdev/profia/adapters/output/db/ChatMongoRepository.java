package io.github.dumijdev.profia.adapters.output.db;

import io.github.dumijdev.profia.adapters.output.db.models.ChatModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMongoRepository extends MongoRepository<ChatModel, String> {
}
