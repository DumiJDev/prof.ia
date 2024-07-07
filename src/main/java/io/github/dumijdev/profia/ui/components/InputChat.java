package io.github.dumijdev.profia.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.dom.Style;

import java.util.function.Consumer;

import static com.vaadin.flow.component.icon.VaadinIcon.ARROW_RIGHT;
import static com.vaadin.flow.component.icon.VaadinIcon.MICROPHONE;

public class InputChat extends Div {
    private final Button sendMessageButton;
    private final Button recordMessageButton;
    private final Input input;

    public InputChat() {
        var inputContainer = new Div();
        setInputContainerStyle(inputContainer.getStyle());

        input = new Input();
        setInputStyle(input.getStyle());

        sendMessageButton = new Button(ARROW_RIGHT.create());
        setButtonStyle(sendMessageButton.getStyle());

        recordMessageButton = new Button(MICROPHONE.create());
        setButtonStyle(recordMessageButton.getStyle());

        var buttonGroup = new Div();
        setButtonGroupStyle(buttonGroup.getStyle());

        buttonGroup.add(sendMessageButton, recordMessageButton);

        inputContainer.add(input, buttonGroup);

        setDisplay(getStyle());

        add(inputContainer);
    }

    public void addSendAction(Consumer<String> sendAction) {
        sendMessageButton.addClickListener(event -> {
            sendAction.accept(input.getValue());
            input.setValue("");
        });
    }

    public void addRecordAction(Consumer<String> recordAction) {
        recordMessageButton.addClickListener(event -> {
            recordAction.accept(input.getValue());
            input.setValue("");
        });
    }

    private void setButtonStyle(Style style) {
        style.setBackgroundColor("transparent")
                .setMargin("0 2px");
    }

    private void setInputStyle(Style style) {
        style.setWidth("90%")
                .setBorder("transparent")
                .setBorderRadius("10px")
                .setBackgroundColor("#3c3c3c")
                .setColor("#fff")
                .setPadding("10px");
    }

    private void setInputContainerStyle(Style style) {
        style.setWidth("90%")
                .setDisplay(Style.Display.FLEX)
                .setJustifyContent(Style.JustifyContent.SPACE_BETWEEN)
                .setAlignItems(Style.AlignItems.CENTER);
    }

    private void setButtonGroupStyle(Style style) {
        style.setDisplay(Style.Display.FLEX);
    }

    private void setDisplay(Style style) {
        style.setWidth("90%");
    }
}
