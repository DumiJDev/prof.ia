package io.github.dumijdev.profia.ui.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.theme.lumo.LumoUtility;
import io.github.dumijdev.profia.application.core.domain.Message;

public class MessageComponent extends Div {
    public MessageComponent(Message message) {
        applyStyle(message);
        setText(message.content());
    }

    private void applyStyle(Message message) {
        var style = getStyle();

        style.setDisplay(Style.Display.FLEX)
                .setAlignItems(Style.AlignItems.CENTER)
                .setJustifyContent(Style.JustifyContent.CENTER)
                .setPadding("8px");

        if (message.sender() == Message.Sender.USER) {
            setClassName(LumoUtility.Background.PRIMARY);
        } else {
            setClassName(LumoUtility.Background.BASE);
        }
    }
}
