package io.github.dumijdev.profia.ui.components;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;

import java.util.List;

import static com.vaadin.flow.component.icon.VaadinIcon.ARCHIVE;
import static com.vaadin.flow.component.icon.VaadinIcon.HOME;

public class SideBarComponent extends SideNav {

    public SideBarComponent() {
        addItem(getLinks().toArray(SideNavItem[]::new));
    }

    private List<SideNavItem> getLinks() {
        return List.of(
                navItem("Home", "/", HOME.create()),
                navItem("History", "/history", ARCHIVE.create())
        );
    }

    private SideNavItem navItem(String label, String path, Icon icon) {
        return new SideNavItem(label, path, icon);
    }
}
