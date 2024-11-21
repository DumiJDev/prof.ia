package io.github.dumijdev.profia.adapters.output.db;

import io.github.dumijdev.profia.adapters.output.db.models.MessageModel;
import io.github.dumijdev.profia.application.core.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMongoRepository extends MongoRepository<MessageModel, String> {
}
