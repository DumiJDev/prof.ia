package io.github.dumijdev.profia.ui.components.message;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.dom.Style.JustifyContent;
import io.github.dumijdev.profia.application.core.domain.Message;

import static com.vaadin.flow.dom.Style.JustifyContent.FLEX_END;
import static com.vaadin.flow.dom.Style.JustifyContent.FLEX_START;
import static com.vaadin.flow.theme.lumo.LumoUtility.Background.BASE;
import static com.vaadin.flow.theme.lumo.LumoUtility.Background.PRIMARY;
import static io.github.dumijdev.profia.application.core.domain.Message.Sender.USER;

public class Item extends Div {
    public Item(Message message) {
        applyStyle(message);

        var messageContainer = new Div(message.content());
        drawMessage(message, messageContainer);

        add(messageContainer);
    }

    private void applyStyle(Message message) {
        var style = getStyle();

        style.setDisplay(Style.Display.FLEX).setAlignItems(Style.AlignItems.CENTER).setBorderRadius("15px");

        if (message.sender() == USER) {
            setClassName(PRIMARY);
            style.setJustifyContent(FLEX_END);
        } else {
            setClassName(BASE);
            style.setJustifyContent(FLEX_START);
        }
    }

    private void drawMessage(Message message, Component component) {
        var style = component.getStyle();

        style.setJustifyContent(JustifyContent.CENTER)
                .setPadding("8px");

        if (message.sender() == USER) {
            component.setClassName(PRIMARY);
        } else {
            component.setClassName(BASE);
        }
    }
}
