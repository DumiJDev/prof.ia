package io.github.dumijdev.profia.adapters.output.ia;

import io.github.dumijdev.profia.application.core.domain.Message;
import lombok.Getter;

import java.util.Stack;

@Getter
public class ChatMemory {
    private final Stack<Message> messages = new Stack<>();
    private static final ChatMemory INSTANCE = new ChatMemory();

    public void addMessage(Message message) {
        if (messages.size() == 20) {
            messages.pop();
        }
        messages.push(message);
    }

    public void clear() {
        messages.clear();
    }

    private ChatMemory() {
    }

    public static ChatMemory getInstance() {
        return INSTANCE;
    }
}
