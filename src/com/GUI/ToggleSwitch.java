package com.GUI;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class ToggleSwitch extends HBox {

    private final Label label = new Label();
    private final Button button = new Button();

    private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(true);
    public SimpleBooleanProperty switchOnProperty() { return switchedOn; }

    private void init() {

        label.setText("ON");

        getChildren().addAll(button, label);
        label.setOnMouseClicked((e) -> {
            switchedOn.set(!switchedOn.get());
        });
        button.setOnAction((e) -> {
            switchedOn.set(!switchedOn.get());
        });
        setStyle();
        bindProperties();
    }

    private void setStyle() {
        //Default Width
        setWidth(80);
        label.setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: black; -fx-text-fill:black; -fx-background-radius: 4;");
        setAlignment(Pos.CENTER_LEFT);
    }

    private void bindProperties() {
        label.prefWidthProperty().bind(widthProperty().divide(2));
        label.prefHeightProperty().bind(heightProperty());
        button.prefWidthProperty().bind(widthProperty().divide(2));
        button.prefHeightProperty().bind(heightProperty());
    }

    public ToggleSwitch() {
        init();
        switchedOn.addListener((observableValue,oldValue,newValue) -> {
            if (newValue) {
                label.setText("ON");
                setStyle("-fx-background-color: black;");
                label.toFront();
            }
            else {
                label.setText("OFF");
                setStyle("-fx-background-color: grey;");
                button.toFront();
            }
        });
    }
}