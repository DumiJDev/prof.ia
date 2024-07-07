package io.github.dumijdev.profia.adapters.out.db;

import io.github.dumijdev.profia.adapters.out.db.models.MessageModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMongoRepository extends MongoRepository<MessageModel, String> {
}
