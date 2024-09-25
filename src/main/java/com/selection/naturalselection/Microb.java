package com.selection.naturalselection;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Microb extends ImageView {
    private double energy;
    private double size;
    private double speed;
    private double interactionRadius;
    private static final Random random = new Random();
    private int foodCount = 0;
    private Simulation simulation;
    private double moveDirectionX;
    private double moveDirectionY;
    private int moveTicks = 0;

    private static final double SIMULATION_WIDTH = 690;
    private static final double SIMULATION_HEIGHT = 690;

    public Microb(double x, double y, double energy, Simulation simulation) {
        super(new Image(Microb.class.getResourceAsStream("/com.selection.naturalselection/micro.png")));
        this.simulation = simulation;
        this.setX(x);
        this.setY(y);
        this.energy = energy;

        this.speed = 0.5 + random.nextDouble();
        this.size = 20 + random.nextDouble();
        this.interactionRadius = 100 + random.nextInt(5);

        updateImageSize();
        applyRandomColor();
        setRandomDirection();
    }

    private void updateImageSize() {
        this.setFitWidth(size);
        this.setFitHeight(size);
    }

    private void applyRandomColor() {
        double hue = random.nextDouble() * 360 - 180;
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(hue / 360.0);
        this.setEffect(colorAdjust);
    }

    private void setRandomDirection() {
        double angle;
        if (isAtEdge()) {
            angle = calculateBounceAngle();
        } else {
            angle = random.nextDouble() * 2 * Math.PI;
        }
        moveDirectionX = Math.cos(angle);
        moveDirectionY = Math.sin(angle);
        moveTicks = 100 + random.nextInt(100);
    }

    private boolean isAtEdge() {
        double x = this.getX();
        double y = this.getY();
        return x <= 0 || x >= SIMULATION_WIDTH - size || y <= 0 || y >= SIMULATION_HEIGHT - size;
    }

    private double calculateBounceAngle() {
        double x = this.getX();
        double y = this.getY();
        double angle = random.nextDouble() * Math.PI;

        if (x <= 0 || x >= SIMULATION_WIDTH - size) {
            moveDirectionX = -moveDirectionX;
            return Math.atan2(moveDirectionY, moveDirectionX);
        }
        if (y <= 0 || y >= SIMULATION_HEIGHT - size) {
            moveDirectionY = -moveDirectionY;
            return Math.atan2(moveDirectionY, moveDirectionX);
        }
        return angle;
    }
}

