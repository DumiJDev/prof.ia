package io.github.dumijdev.profia.ui.components.message;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.theme.lumo.LumoUtility;
import io.github.dumijdev.profia.application.core.domain.Message;

public class Item extends Div {
    public Item(Message message) {
        applyStyle(message);

        var messageContainer = new Div(message.content());
        drawMessage(message, messageContainer);

        add(messageContainer);
    }

    private void applyStyle(Message message) {
        var style = getStyle();

        style.setDisplay(Style.Display.FLEX)
                .setAlignItems(Style.AlignItems.CENTER);

        if (message.sender() == Message.Sender.USER) {
            setClassName(LumoUtility.Background.PRIMARY);
            style.setJustifyContent(Style.JustifyContent.FLEX_END);
        } else {
            setClassName(LumoUtility.Background.BASE);
            style.setJustifyContent(Style.JustifyContent.FLEX_START);
        }
    }

    private void drawMessage(Message message, Component component) {
        var style = component.getStyle();

        style.setJustifyContent(Style.JustifyContent.CENTER)
                .setPadding("8px");

        if (message.sender() == Message.Sender.USER) {
            component.setClassName(LumoUtility.Background.PRIMARY);
        } else {
            component.setClassName(LumoUtility.Background.BASE);
        }
    }
}
