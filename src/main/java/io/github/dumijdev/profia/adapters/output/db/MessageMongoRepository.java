package io.github.dumijdev.profia.adapters.output.db;

import io.github.dumijdev.profia.adapters.output.db.models.MessageModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMongoRepository extends MongoRepository<MessageModel, String> {
}
