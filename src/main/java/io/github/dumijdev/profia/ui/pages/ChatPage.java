package io.github.dumijdev.profia.ui.pages;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import io.github.dumijdev.profia.application.core.domain.Message;
import io.github.dumijdev.profia.application.core.domain.Page;
import io.github.dumijdev.profia.application.ports.in.FindAllMessagesInputPort;
import io.github.dumijdev.profia.application.ports.in.SaveMessageInputPort;
import io.github.dumijdev.profia.application.ports.in.SendMessageInputPort;
import io.github.dumijdev.profia.ui.components.InputChat;
import io.github.dumijdev.profia.ui.components.message.MessageView;
import io.github.dumijdev.profia.ui.layout.MainLayout;

import java.util.List;

@PageTitle("ProfIA | Chat")
@Route(value = "/chat", layout = MainLayout.class)
public class ChatPage extends Main {
    private final SendMessageInputPort sendMessageService;
    private final SaveMessageInputPort saveMessageService;
    private final FindAllMessagesInputPort findAllMessages;
    private List<Message> messages;
    private final String id = "test";
    private final MessageView messagesView = new MessageView();

    public ChatPage(SendMessageInputPort sendMessageService,
                    SaveMessageInputPort saveMessageService,
                    FindAllMessagesInputPort findAllMessagesInputPort) {
        this.sendMessageService = sendMessageService;
        this.saveMessageService = saveMessageService;
        this.findAllMessages = findAllMessagesInputPort;

        setMessageContainerStyle(messagesView.getStyle());

        setDisplay(getStyle());

        var input = new InputChat();
        input.addSendAction(this::sendMessage);
        input.addRecordAction(this::sendMessage);

        add(messagesView, input);

        updateMessages(0);
    }

    private void sendMessage(String message) {

        var chatMessage = new Message(id, message, Message.Sender.USER);

        var iaResponse = sendMessageService.send(chatMessage);

        saveMessageService.save(chatMessage);

        saveMessageService.save(iaResponse);

        UI.getCurrent().access(() -> {
            updateMessages(0);
        });

    }

    private void setDisplay(Style style) {
        style.setPosition(Style.Position.RELATIVE)
                .setDisplay(Style.Display.FLEX)
                .setFlexDirection(Style.FlexDirection.COLUMN)
                .setJustifyContent(Style.JustifyContent.CENTER)
                .setAlignItems(Style.AlignItems.CENTER)
                .setMinHeight("calc(100vh - 60px)");
    }

    private void setMessageContainerStyle(Style style) {
        style.setDisplay(Style.Display.GRID)
                .set("grid-template-columns", "1fr")
                .setWidth("90%")
                .setMaxHeight("80vh")
                .setMinHeight("80vh");
    }

    private void updateMessages(int page) {
        var messages = this.findAllMessages.findAllMessages(new Page(page));

        messagesView.setItems(messages);
    }
}
