package io.github.dumijdev.profia.adapters.output.ia;

import com.github.tjake.jlama.model.functions.Generator;
import com.github.tjake.jlama.safetensors.prompt.PromptContext;
import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.ports.out.SendMessageOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static io.github.dumijdev.profia.application.core.domain.Message.Sender.IA;
import static io.github.dumijdev.profia.application.core.domain.Message.Sender.USER;
import static java.util.UUID.fromString;

@Component
@RequiredArgsConstructor
@Slf4j
public class JLamaChatAdapter implements SendMessageOutputPort {
    private final Generator ia;
    private final ChatMemory chatMemory = ChatMemory.getInstance();

    @Override
    public Message send(Message message) {

        log.info("Message: {}", message);

        var response = normalize(chat(message));

        log.info("Response: {}", response);

        return new Message(message.chatId(), response, IA);
    }

    private PromptContext context(Message message) {
        chatMemory.addMessage(message);
        var promptSupport = ia.promptSupport();
        if (promptSupport.isPresent()) {
            var builder = promptSupport.get().builder()
                    .addSystemMessage("You are a great english teacher")
                    .addSystemMessage("You teach: Grammar, phonetic, daily sentences")
                    .addSystemMessage("You teach using conversation about daily routine, sports, anime, news, technology")
                    .addSystemMessage("You fix the student when necessary")
                    .addSystemMessage("Your fullname is Dumilde Paulo Fernando")
                    .addSystemMessage("Your friends call you DumiJ")
                    .addSystemMessage("Speak portuguese when necessary");

            chatMemory.getMessages().forEach(message1 -> {
                System.out.println(message1);
                if (message1.sender() == USER) {
                    builder.addUserMessage(message1.content());
                } else if (message1.sender() == IA) {
                    builder.addAssistantMessage(message1.content());
                }
            });

            return builder.build();
        } else {
            return PromptContext.of(message.content());
        }
    }

    private String chat(Message message) {
        var context = context(message);

        var response = ia.generate(fromString(message.chatId()), context, .7f, 5000, (s, aFloat) -> {
        });

        return response.responseText;
    }

    private String normalize(String content) {
        return content.replaceAll("Ãª", "ê")
                .replaceAll("Ã¡", "á")
                .replaceAll("Ã£", "ã")
                .replaceAll("Ã§", "ç")
                .replaceAll("Ã\\u00ad", "í")
                .replaceAll("Ã©", "é")
                .replaceAll("\\n", "<b />");
    }
}
