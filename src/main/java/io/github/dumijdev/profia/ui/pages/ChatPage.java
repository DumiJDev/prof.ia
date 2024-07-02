package io.github.dumijdev.profia.ui.pages;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.Route;
import io.github.dumijdev.profia.ui.layout.MainLayout;

@Route(value = "/chat", layout = MainLayout.class)
public class ChatPage extends Main {
    public ChatPage() {
        var messagesContainer = new Div();

    }
}
