package com.example.gui.data;

import com.example.gui.enums.Difficulty;
import com.example.gui.models.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameData {

    /// Obstacles and decoys for Level 1
    private static Obstacle[] obstacles1 = new Obstacle[]{};
    private static SceneData sceneData1 = new SceneData(obstacles1, 0);

    /// Obstacles and decoys for Level 2
    private static Obstacle[] obstacles2 = new Obstacle[]{
            ///Top middle
            new Obstacle(new Point(5,1)),
            new Obstacle(new Point(6,1)),
            new Obstacle(new Point(7,1)),
            new Obstacle(new Point(8,1)),
            new Obstacle(new Point(9,1)),
            new Obstacle(new Point(10,1)),
            new Obstacle(new Point(11,1)),
            new Obstacle(new Point(12,1)),
            new Obstacle(new Point(13,1)),
            new Obstacle(new Point(14,1)),

            /// middle left
            new Obstacle(new Point(9,2)),
            new Obstacle(new Point(9,3)),
            new Obstacle(new Point(9,4)),
            new Obstacle(new Point(9,15)),
            new Obstacle(new Point(9,16)),
            new Obstacle(new Point(9,17)),

            ///middle right
            new Obstacle(new Point(10,2)),
            new Obstacle(new Point(10,3)),
            new Obstacle(new Point(10,4)),
            new Obstacle(new Point(10,15)),
            new Obstacle(new Point(10,16)),
            new Obstacle(new Point(10,17)),

            /// bottom middle
            new Obstacle(new Point(5,18)),
            new Obstacle(new Point(6,18)),
            new Obstacle(new Point(7,18)),
            new Obstacle(new Point(8,18)),
            new Obstacle(new Point(9,18)),
            new Obstacle(new Point(10,18)),
            new Obstacle(new Point(11,18)),
            new Obstacle(new Point(12,18)),
            new Obstacle(new Point(13,18)),
            new Obstacle(new Point(14,18)),

            new Obstacle(new Point(2,7), true),
            new Obstacle(new Point(2,8), true),
            new Obstacle(new Point(2,9), true),
            new Obstacle(new Point(2,10), true),
            new Obstacle(new Point(3,7), true),
            new Obstacle(new Point(3,8), true),
            new Obstacle(new Point(3,9), true),
            new Obstacle(new Point(3,10), true),

            new Obstacle(new Point(16,7), true),
            new Obstacle(new Point(16,8), true),
            new Obstacle(new Point(16,9), true),
            new Obstacle(new Point(16,10), true),
            new Obstacle(new Point(17,7), true),
            new Obstacle(new Point(17,8), true),
            new Obstacle(new Point(17,9), true),
            new Obstacle(new Point(17,10), true),

    };
    private static SceneData sceneData2 = new SceneData(obstacles2, 8);

    private static Obstacle[] obstacles3 = new Obstacle[]{
            ///Top middle
            new Obstacle(new Point(5,1), Color.BROWN,Color.BROWN),
            new Obstacle(new Point(6,1),Color.BROWN,Color.BROWN),
            new Obstacle(new Point(7,1),Color.BROWN,Color.BROWN),
            new Obstacle(new Point(8,1),Color.BROWN,Color.BROWN),
            new Obstacle(new Point(9,1),Color.BROWN,Color.BROWN),
            new Obstacle(new Point(10,1),Color.BROWN,Color.BROWN),
            new Obstacle(new Point(11,1),Color.BROWN,Color.BROWN),
            new Obstacle(new Point(12,1),Color.BROWN,Color.BROWN),
            new Obstacle(new Point(13,1),Color.BROWN,Color.BROWN),
            new Obstacle(new Point(14,1),Color.BROWN,Color.DARKORANGE),

            /// middle left
            new Obstacle(new Point(9,2)),
            new Obstacle(new Point(9,3)),
            new Obstacle(new Point(9,4)),
            new Obstacle(new Point(9,5), true),
            new Obstacle(new Point(9,6),true),
            new Obstacle(new Point(9,7),true),
            new Obstacle(new Point(9,8),true),

            new Obstacle(new Point(9,11),true),
            new Obstacle(new Point(9,12),true),
            new Obstacle(new Point(9,13),true),
            new Obstacle(new Point(9,14),true),
            new Obstacle(new Point(9,15)),
            new Obstacle(new Point(9,16)),
            new Obstacle(new Point(9,17)),

            ///middle right
            new Obstacle(new Point(10,2)),
            new Obstacle(new Point(10,3)),
            new Obstacle(new Point(10,4)),
            new Obstacle(new Point(10,5),true),
            new Obstacle(new Point(10,6),true),
            new Obstacle(new Point(10,7),true),
            new Obstacle(new Point(10,8),true),

            new Obstacle(new Point(10,11),true),
            new Obstacle(new Point(10,12),true),
            new Obstacle(new Point(10,13),true),
            new Obstacle(new Point(10,14),true),
            new Obstacle(new Point(10,15)),
            new Obstacle(new Point(10,16)),
            new Obstacle(new Point(10,17)),

            /// bottom middle
            new Obstacle(new Point(5,18)),
            new Obstacle(new Point(6,18)),
            new Obstacle(new Point(7,18)),
            new Obstacle(new Point(8,18)),
            new Obstacle(new Point(9,18)),
            new Obstacle(new Point(10,18)),
            new Obstacle(new Point(11,18)),
            new Obstacle(new Point(12,18)),
            new Obstacle(new Point(13,18)),
            new Obstacle(new Point(14,18)),

    };

    private static SceneData sceneData3 = new SceneData(obstacles3, 10);






    public static Level[] levels = new Level[]{
            new Level("Level 1", Difficulty.Easy, sceneData1, 100),
            new Level("Level 2", Difficulty.Medium, sceneData2, 80),
            new Level("Level 3", Difficulty.Hard, sceneData3, 80, Color.web("373A40"), Color.web("686D76")),

    };


}
