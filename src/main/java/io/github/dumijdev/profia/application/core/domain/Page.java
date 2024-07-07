package io.github.dumijdev.profia.application.core.domain;

public record Page(int page, int size) {
    public Page(int page) {
        this(page, 10);
    }
}
