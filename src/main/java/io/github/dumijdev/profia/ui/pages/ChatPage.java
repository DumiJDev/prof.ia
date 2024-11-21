package io.github.dumijdev.profia.ui.pages;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.dom.Style.Display;
import com.vaadin.flow.dom.Style.Position;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import io.github.dumijdev.profia.adapters.output.ia.ChatMemory;
import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.core.domain.Page;
import io.github.dumijdev.profia.application.ports.in.FindMessagesByChatIdInputPort;
import io.github.dumijdev.profia.application.ports.in.SaveMessageInputPort;
import io.github.dumijdev.profia.application.ports.in.SendMessageInputPort;
import io.github.dumijdev.profia.ui.components.InputChat;
import io.github.dumijdev.profia.ui.components.message.MessageView;
import io.github.dumijdev.profia.ui.layout.MainLayout;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import static com.vaadin.flow.component.UI.getCurrent;
import static com.vaadin.flow.dom.Style.AlignItems.CENTER;
import static com.vaadin.flow.dom.Style.Display.FLEX;
import static com.vaadin.flow.dom.Style.Display.GRID;
import static com.vaadin.flow.dom.Style.FlexDirection.*;
import static com.vaadin.flow.dom.Style.JustifyContent.*;
import static com.vaadin.flow.dom.Style.Position.RELATIVE;
import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor;

@PageTitle("ProfIA | Chat")
@Route(value = "/chat", layout = MainLayout.class)
public class ChatPage extends Main implements HasUrlParameter<String> {
    private final SendMessageInputPort sendMessageService;
    private final SaveMessageInputPort saveMessageService;
    private final FindMessagesByChatIdInputPort findAllMessages;
    private final MessageView messagesView = new MessageView();

    public ChatPage(SendMessageInputPort sendMessageService,
                    SaveMessageInputPort saveMessageService,
                    FindMessagesByChatIdInputPort findMessagesByChatIdInputPort) {
        this.sendMessageService = sendMessageService;
        this.saveMessageService = saveMessageService;
        this.findAllMessages = findMessagesByChatIdInputPort;

    }

    private void sendMessage(String chatId, String message) {

        var chatMessage = new Message(chatId, message, Message.Sender.USER);
        saveMessageService.save(chatMessage);

        var iaResponse = sendMessageService.send(chatMessage);
        saveMessageService.save(iaResponse);

        getCurrent().access(() -> updateMessages(chatId));

    }

    private void setDisplay(Style style) {
        style.setPosition(RELATIVE)
                .setDisplay(FLEX)
                .setFlexDirection(COLUMN)
                .setJustifyContent(Style.JustifyContent.CENTER)
                .setAlignItems(CENTER)
                .setMinHeight("calc(100vh - 60px)");
    }

    private void setMessageContainerStyle(Style style) {
        style.setDisplay(GRID)
                .set("grid-template-columns", "1fr")
                .setWidth("90%")
                .setMaxHeight("80vh")
                .setMinHeight("80vh");
    }

    private void updateMessages(String chatId) {
        var messages = this.findAllMessages.findMessagesByChatId(chatId);
        var chatMemory = ChatMemory.getInstance();
        chatMemory.clear();

        messages.stream().sorted(Comparator.comparing(Message::datetime))
                        .limit(20).forEach(chatMemory::addMessage);

        messagesView.setItems(messages);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String chatId) {
        setMessageContainerStyle(messagesView.getStyle());

        setDisplay(getStyle());

        var input = new InputChat();
        var action = createSendAction(chatId);
        input.addSendAction(action);
        input.addRecordAction(action);

        add(messagesView, input);

        updateMessages(chatId);

    }

    private Consumer<String> createSendAction(String chatId) {
        return s -> sendMessage(chatId, s);
    }
}
