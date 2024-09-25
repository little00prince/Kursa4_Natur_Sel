package com.selection.naturalselection;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class StatisticPane extends VBox {
    private Label titleLabel;
    private Label FoodSpawnLabel;
    private Label TimeSpawnLabel;
    private TextField foodSpawnTextField;
    private TextField TimeSpawnTextField;
    private Label SimulationEditionLabel;
    public Button PauseButton;
    private Label energyDepletionDeathsLabel;
    private Label predationDeathsLabel;
    private Label birthsLabel;
    private Label currentAnimalsLabel;

    public StatisticPane() {

        titleLabel = new Label("Статистика");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        energyDepletionDeathsLabel = new Label("С: 0   ");
        energyDepletionDeathsLabel.setFont(Font.font("Arial", FontPosture.REGULAR, 14));

        predationDeathsLabel = new Label("Съедено хищниками: 0");
        predationDeathsLabel.setFont(Font.font("Arial", FontPosture.REGULAR, 14));

        birthsLabel = new Label("Родилось микробов: 0    ");
        birthsLabel.setFont(Font.font("Arial", FontPosture.REGULAR, 14));

        currentAnimalsLabel = new Label("Микробов в симуляции: 0");
        currentAnimalsLabel.setFont(Font.font("Arial", FontPosture.REGULAR, 14));

        FoodSpawnLabel = new Label("Количество появляющейся еды");
        FoodSpawnLabel.setFont(Font.font("Arial", FontPosture.REGULAR, 12));

        foodSpawnTextField = new TextField("20");
        foodSpawnTextField.setFont(Font.font("Arial", FontPosture.REGULAR, 14));

        foodSpawnTextField.setPrefWidth(80);
        foodSpawnTextField.setMaxWidth(80);

        SimulationEditionLabel = new Label("Настройка спавна еды");
        SimulationEditionLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        TimeSpawnLabel = new Label("Кол-во секунд до новой еды");
        TimeSpawnLabel.setFont(Font.font("Arial", FontPosture.REGULAR, 12));

        TimeSpawnTextField = new TextField("10");
        TimeSpawnTextField.setFont(Font.font("Arial", FontPosture.REGULAR, 14));
        TimeSpawnTextField.setPrefWidth(80);
        TimeSpawnTextField.setMaxWidth(80);

        PauseButton = new Button("Пауза симуляции");
        PauseButton.setStyle("-fx-background-color: #FF6347; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 14px; " +
                "-fx-padding: 10px 20px; " +
                "-fx-border-color: transparent; " +
                "-fx-border-radius: 5px;");

        HBox BoxN2 = new HBox(TimeSpawnLabel, TimeSpawnTextField);
        BoxN2.setSpacing(10);

        HBox foodSpawnBox = new HBox(FoodSpawnLabel, foodSpawnTextField);
        foodSpawnBox.setSpacing(10);

        HBox BoxN3 = new HBox(energyDepletionDeathsLabel, predationDeathsLabel);
        BoxN3.setSpacing(10);

        HBox BoxN4 = new HBox(birthsLabel,  currentAnimalsLabel);
        BoxN4.setSpacing(10);

        this.getChildren().addAll(
                titleLabel, BoxN3, BoxN4, SimulationEditionLabel, FoodSpawnLabel, foodSpawnTextField,
                TimeSpawnLabel, TimeSpawnTextField, foodSpawnBox, BoxN2, PauseButton);
    }

    //Метод для получения значения количества пищи, которое ввел пользователь
    public TextField getFoodSpawnAmount() {
        String text = foodSpawnTextField.getText().trim();
        if (text.isEmpty()) {
            foodSpawnTextField.setText("0");
            return foodSpawnTextField;
        }
        try {
            int amount = Integer.parseInt(text); // Пытаемся преобразовать текст в число
            if (amount < 150) {
                return foodSpawnTextField;
            } else {
                foodSpawnTextField.setText("15");
                return foodSpawnTextField;
            }
        } catch (NumberFormatException e) {
            foodSpawnTextField.setText("15");
            return foodSpawnTextField;
        }

    }

    //Метод получения времени до новой пищи, которое ввел пользователь
    public TextField getTimeSpawnTextField() {
        String text = TimeSpawnTextField.getText().trim();
        if (text.isEmpty()) {
            TimeSpawnTextField.setText("0");
            return TimeSpawnTextField;
        }
        try {
            int amount = Integer.parseInt(text); // Пытаемся преобразовать текст в число
            if (amount < 150) {
                return TimeSpawnTextField;
            } else {
                TimeSpawnTextField.setText("15");
                return TimeSpawnTextField;
            }
        } catch (NumberFormatException e) {
            TimeSpawnTextField.setText("15");
            return TimeSpawnTextField;
        }

    }

    public int getTimeSpawnAmount() {
        String text = TimeSpawnTextField.getText().trim();

        int amount = Integer.parseInt(text); // Пытаемся преобразовать текст в число
        return amount; // Возвращаем число, если оно меньше 150
    }

    public void updateEnergyDepletionDeaths(int count) {
        energyDepletionDeathsLabel.setText("Умерли от голода: " + count);
    }

    public void updateNewAnimals(int count) {
        birthsLabel.setText("Появилось новых микробов: " + count);
    }

    public void updateCurrentAnimals(int count) {
        currentAnimalsLabel.setText("В симуляции: " + count);
    }

    public void updatePredationDeaths(int count) {
        predationDeathsLabel.setText("   Съедено хищниками: " + count);
    }



}
