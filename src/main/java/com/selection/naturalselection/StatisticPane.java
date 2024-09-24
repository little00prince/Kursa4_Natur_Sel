package com.selection.naturalselection;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StatisticPane extends VBox {
    private Label titleLabel;

    public StatisticPane() {
        titleLabel = new Label("Статистика");

        this.getChildren().addAll(
                titleLabel);
    }
}
