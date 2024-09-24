package com.selection.naturalselection;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation extends Application{
    private Pane simulationPane = new Pane();
    private StatisticPane statisticPane = new StatisticPane();
    private Random random = new Random();
    private List<Food> foods = new ArrayList<>();
    private boolean isPaused = false;
    private Timeline foodSpawnTimer; // Таймер для спауна пищи

    private static final double SIMULATION_WIDTH = 700;
    private static final double SIMULATION_HEIGHT = 700;
    private static final double BORDER_MARGIN = 50;

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

        spawnFood(20, simulationPane); // Спавн 20 единиц пищи

        // Обработчик изменения времени до новой пищи
        statisticPane.getTimeSpawnTextField().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isPaused) {
                initializeFoodSpawnTimer(); // Переинициализация таймера при изменении значения, только если не на паузе
            }
        });

        statisticPane.getFoodSpawnAmount().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isPaused) {
                initializeFoodSpawnTimer(); // Переинициализация таймера при изменении значения, только если не на паузе
            }
        });

        // Инициализация таймера для спауна пищи
        if(!isPaused) {
            initializeFoodSpawnTimer();

            // Создание и запуск таймера обновления симуляции
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (!isPaused) {
                        // Логика обновления симуляции
                        updateSimulation(simulationPane, statisticPane);
                    }
                }
            };

            timer.start();

            // Обработчик кнопки паузы/возобновления симуляции
            statisticPane.PauseButton.setOnAction(event -> {
                isPaused = !isPaused;
                if (isPaused) {
                    foodSpawnTimer.pause(); // При паузе останавливаем таймер спавна пищи
                    timer.stop(); // Останавливаем таймер обновления симуляции
                    statisticPane.PauseButton.setText("Возобновить симуляцию");
                } else {
                    foodSpawnTimer.play(); // При возобновлении запускаем таймер спавна пищи
                    timer.start(); // Запускаем таймер обновления симуляции
                    statisticPane.PauseButton.setText("Пауза симуляции");
                }
            });
        }

    }

    private void initializeFoodSpawnTimer() {
        int timeSpawnAmount = statisticPane.getTimeSpawnAmount();
        if(!isPaused) {
            if (foodSpawnTimer != null) {
                foodSpawnTimer.stop(); // Останавливаем текущий таймер, если он существует
            }


            String text = statisticPane.getFoodSpawnAmount().getText().trim();
            int FoodSpawnAmount = Integer.parseInt(text);// Получаем текущее значение из текстового поля
            foodSpawnTimer = new Timeline(
                    new KeyFrame(Duration.seconds(timeSpawnAmount), event -> spawnFood(FoodSpawnAmount, simulationPane))
            );
            foodSpawnTimer.setCycleCount(Timeline.INDEFINITE);
            foodSpawnTimer.play();
        }

    }

    private ImageView createBackground(String path) {
        Image image = new Image(new File(path).toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(SIMULATION_WIDTH);
        imageView.setFitHeight(SIMULATION_HEIGHT);
        imageView.setPreserveRatio(false);
        return imageView;
    }

    private void spawnFood(int count, Pane simulationPane) {
        for (int i = 0; i < count; i++) {
            double x = BORDER_MARGIN + random.nextDouble() * (SIMULATION_WIDTH - 2 * BORDER_MARGIN);
            double y = BORDER_MARGIN + random.nextDouble() * (SIMULATION_HEIGHT - 2 * BORDER_MARGIN);
            Food food = new Food(x, y);
            foods.add(food);
            simulationPane.getChildren().add(food);
        }
    }

    private void updateSimulation(Pane simulationPane, StatisticPane statisticPane) {

    }




        public static void main(String[] args){
        launch(args);
    }

}
