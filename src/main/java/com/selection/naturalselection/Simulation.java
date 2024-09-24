package com.selection.naturalselection;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;

public class Simulation extends Application{
    private Pane simulationPane = new Pane();
    private StatisticPane statisticPane = new StatisticPane();

    private static final double SIMULATION_WIDTH = 700;
    private static final double SIMULATION_HEIGHT = 700;

    @Override
    public void start(Stage primaryStage) {
        //Иконка
        Image image = new Image(new File("micro.png").toURI().toString());
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        ImageView background = createBackground("C:\\Kursa4_Natural_selection\\src\\main\\resources\\fon.jpg");
        simulationPane.getChildren().add(background);

        // Создание корневого макета
        HBox root = new HBox();
        root.getChildren().addAll(statisticPane, simulationPane);

        // Создание поля
        Scene scene = new Scene(root, 1100,700);
        primaryStage.setTitle("Симуляция естественного отбора");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ImageView createBackground(String path) {
        Image image = new Image(new File(path).toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(SIMULATION_WIDTH);
        imageView.setFitHeight(SIMULATION_HEIGHT);
        imageView.setPreserveRatio(false);
        return imageView;
    }

    public static void main(String[] args){
        launch(args);
    }

}
