package io.github.dumijdev.profia.ui.components.message;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.dom.Style;
import io.github.dumijdev.profia.application.core.domain.Message;

import java.util.LinkedList;
import java.util.List;

public class MessageView extends VirtualList<Message> {
    private List<Message> messages = new LinkedList<>();

    public MessageView() {
        setRenderer(createRenderer());
        applyGeneralStyle(getStyle());
    }

    private ComponentRenderer<Component, Message> createRenderer() {
        return new ComponentRenderer<>(Item::new);
    }

    private void applyGeneralStyle(Style style) {}

    public MessageView(List<Message> messages) {
        this.messages = messages;

        setItems(messages);
        setRenderer(createRenderer());
    }

    public void addItem(Message message) {
        messages.add(message);
    }

    public void removeItem(Message message) {
        messages.remove(message);
    }

    public void clearItems() {
        messages.clear();
    }

}
