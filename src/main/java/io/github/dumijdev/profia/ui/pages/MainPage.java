package io.github.dumijdev.profia.ui.pages;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.dom.Style.AlignItems;
import com.vaadin.flow.dom.Style.JustifyContent;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import io.github.dumijdev.profia.ui.layout.MainLayout;

import static com.vaadin.flow.component.UI.getCurrent;
import static com.vaadin.flow.component.icon.VaadinIcon.ROCKET;
import static com.vaadin.flow.dom.Style.Display.FLEX;
import static java.util.UUID.randomUUID;

@PageTitle("ProfIA | Chat")
@Route(value = "/", layout = MainLayout.class)
public class MainPage extends Main {
    public MainPage() {
        var mainBt = new Button("Get Started", ROCKET.create(), this::handleAction);

        mainBt.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.MATERIAL_CONTAINED);

        add(mainBt);

        getStyle().setDisplay(FLEX).setJustifyContent(JustifyContent.CENTER).setAlignItems(AlignItems.CENTER)
                .setMinHeight("90vh");
    }

    private void handleAction(ClickEvent<Button> event) {
        getCurrent().navigate("/chat/%s".formatted(randomUUID().toString()));
    }
}
