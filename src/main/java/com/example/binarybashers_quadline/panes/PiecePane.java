package com.example.binarybashers_quadline.panes;

import com.example.binarybashers_quadline.Settings;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.Serializable;

public class PiecePane extends HBox {
    private int radius = 50;
    private Circle circle = new Circle(radius);
    private Color color = Settings.defaultPieceColor;
    private  Color stroke = Settings.defaultStrokeColor;

    public PiecePane() {
        getChildren().add(circle);
        circle.setStroke(stroke);
        circle.setFill(color);
    }

    public PiecePane(Color color){
        getChildren().add(circle);

        circle.setStroke(stroke);
        circle.setFill(color);

        this.color = color;
    }

    public PiecePane(Color color, Color stroke){
        getChildren().add(circle);

        circle.setStroke(stroke);
        circle.setFill(color);

        this.stroke = stroke;
        this.color = color;
    }

    public PiecePane(Color color, Color stroke, int radius){
        getChildren().add(circle);

        circle.setStroke(stroke);
        circle.setFill(color);
        circle.setRadius(radius);

        this.stroke = stroke;
        this.color = color;
        this.radius =radius;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        circle.setFill(color);
    }

    public Color getStroke() {
        return stroke;
    }

    public void setStroke(Color stroke) {
        this.stroke = stroke;
        circle.setStroke(stroke);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        circle.setRadius(radius);
    }
}
