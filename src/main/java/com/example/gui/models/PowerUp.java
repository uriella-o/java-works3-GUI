package com.example.gui.models;

import com.example.gui.Snakegame;
import com.example.gui.enums.Power;
import javafx.scene.image.Image;

public class PowerUp {
    public Power power;

    public Image icon;

    public PowerUp(Power power){
        this.power = power;

        if(power== Power.Body){
            icon = new Image(Snakegame.class.getResourceAsStream("img/scissors.png"));
        }
        else if(power==Power.Shield){
            icon = new Image(Snakegame.class.getResourceAsStream("img/shield.png"));
        }
        else if(power==Power.Life){
            icon = new Image(Snakegame.class.getResourceAsStream("img/heart.png"));
        }
        else{
            icon = new Image(Snakegame.class.getResourceAsStream("img/turtle.png"));

        }
    }
}
