package com.example.gui.models;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Decoy {
    public Point point;

    public Image image;


    public Decoy(Point point, String icon){
        this.point = point;
        image = new Image(getClass().getResourceAsStream("img/"+icon));
    }

}
