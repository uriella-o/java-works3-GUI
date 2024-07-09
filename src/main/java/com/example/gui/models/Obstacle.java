package com.example.gui.models;

import javafx.scene.paint.Color;

public class Obstacle {
    public Point point;
    public int scaleFactor = 1;
    public Color color = Color.web("373A40");
    public Color secondaryColor = Color.web("686D76");

    public boolean tree = false;

    public Obstacle(Point point){
        this.point = point;
    }

    public Obstacle(Point point, int scaleFactor){
        this.point = point;
        this.scaleFactor = scaleFactor;
    }

    public Obstacle(Point point, boolean tree){
        this.point = point;
        this.tree = tree;
    }

    public Obstacle(Point point, Color color, Color secondaryColor){
        this.point = point;
        this.color = color;
        this.secondaryColor = secondaryColor;
    }

    public Obstacle(Point point, int scaleFactor, Color color){
        this.point = point;
        this.scaleFactor = scaleFactor;
        this.color = color;
    }

}
