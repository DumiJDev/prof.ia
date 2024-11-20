package io.github.dumijdev.profia.infra;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import io.github.dumijdev.profia.adapters.output.ia.Assistant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {
    /**
     * This chat memory will be used by an {@link Assistant}
     */
    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.withMaxMessages(10);
    }

    @Bean
    public Assistant assistant() {
        return AiServices.builder(Assistant.class)
                .chatMemory(chatMemory())
                .build();
    }
}
