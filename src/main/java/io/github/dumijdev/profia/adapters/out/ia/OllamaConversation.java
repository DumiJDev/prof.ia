package io.github.dumijdev.profia.adapters.out.ia;

import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.ports.out.SendMessageOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OllamaConversation implements SendMessageOutputPort {
    private final OllamaChatModel ia;

    @Override
    public Message send(Message message) {

        log.info("Message: {}", message);

        var response = ia.call(new Prompt(List.of(
                new UserMessage("I'm learning english, fix me if all wrong words"),
                new UserMessage(message.content())
        )));

        var content = response.getResult().getOutput().getContent();

        log.info("Response: {}", content);

        return new Message(message.chatId(), content, Message.Sender.IA);
    }
}
