package com.example.gui.models;

import java.util.List;

public class SceneData {

    public Obstacle[] obstacles;

    public int decoys;

    public SceneData(Obstacle[] obstacles, int decoys){
        this.obstacles = obstacles;
        this.decoys = decoys;
    }

}
