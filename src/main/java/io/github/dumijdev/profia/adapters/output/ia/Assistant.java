package io.github.dumijdev.profia.adapters.output.ia;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface Assistant {
    @SystemMessage("You are a great teacher english")
    String chat(String userMessage);
}
