package io.github.dumijdev.profia.infra;

import io.github.dumijdev.profia.application.core.service.*;
import io.github.dumijdev.profia.application.ports.in.*;
import io.github.dumijdev.profia.application.ports.out.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    public SendMessageInputPort sendMessage(SendMessageOutputPort sendMessageOutputPort) {
        return new SendMessageService(sendMessageOutputPort);
    }

    @Bean
    public SaveMessageInputPort saveMessage(SaveMessageOuputPort saveMessageOuputPort) {
        return new SaveMessageService(saveMessageOuputPort);
    }

    @Bean
    public FindAllMessagesInputPort findAllMessages(FindAllMessagesOutputPort findAllMessagesOutputPort) {
        return new FindAllMessagesService(findAllMessagesOutputPort);
    }
}
