package io.github.dumijdev.profia.ui.pages;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import io.github.dumijdev.profia.application.core.domain.Chat;
import io.github.dumijdev.profia.application.ports.in.FindChatsInputPort;
import io.github.dumijdev.profia.ui.layout.MainLayout;

@PageTitle("ProfIA | Chat")
@Route(value = "/history", layout = MainLayout.class)
public class HistoryPage extends VirtualList<Chat> {
    public HistoryPage(FindChatsInputPort findChats) {
        setRenderer(createHistoryItem());
        setItems(findChats.findChats().stream());
    }

    private ComponentRenderer<Div, Chat> createHistoryItem() {
        return new ComponentRenderer<>(Div::new, (div, chat1) -> {
            div.add(new Anchor("/profia/chat/%s".formatted(chat1.id()), chat1.title()));
        });
    }

}
