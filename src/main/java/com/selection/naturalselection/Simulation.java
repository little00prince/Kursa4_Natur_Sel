package com.selection.naturalselection;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Simulation extends Application{

    @Override
    public void start(Stage primaryStage) {

        HBox root = new HBox();
        Scene scene = new Scene(root, 1100,700);
        primaryStage.setTitle("Симуляция естественного отбора");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
