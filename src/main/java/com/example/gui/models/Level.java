package com.example.gui.models;

import com.example.gui.enums.Difficulty;
import com.example.gui.enums.Direction;
import javafx.scene.paint.Color;

public class Level {
    public String name;
    public Difficulty difficulty;
    public SceneData sceneData;

    public Color tileColor;
    public Color secondaryTileColor;

    public int maxScore;


    public Level(String name, Difficulty difficulty, SceneData sceneData, int maxScore){
        this.name = name;
        this.difficulty = difficulty;
        this.sceneData = sceneData;
        this.tileColor = Color.web("AAD751");
        this.secondaryTileColor = Color.web("A2D149");
        this.maxScore = maxScore;
    }

    public Level(String name, Difficulty difficulty, SceneData sceneData, int maxScore, Color tileColor,Color secondaryTileColor){
        this.name = name;
        this.difficulty = difficulty;
        this.sceneData = sceneData;
        this.tileColor = tileColor;
        this.secondaryTileColor = secondaryTileColor;
        this.maxScore = maxScore;
    }
}
