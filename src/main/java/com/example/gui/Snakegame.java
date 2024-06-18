package com.example.gui;

import com.example.gui.data.AssetsData;
import com.example.gui.data.GameData;
import com.example.gui.enums.Direction;
import com.example.gui.enums.Power;
import com.example.gui.models.Level;
import com.example.gui.models.Obstacle;
import com.example.gui.models.Point;
import com.example.gui.models.PowerUp;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Snakegame extends Application {

    public static final int width = 800;
    public static final int height = width;
    public static final int rows = 20;
    public static final int columns = 20;
    public static final int square_size = width/columns;

    private int score =0, highestScore;

    private Timeline game;

    private static final String[] food_images = AssetsData.foodImages;
    private static final String[] decoy_images = AssetsData.decoyImages;
    private static final String[] power_up_images = AssetsData.powerUpImages;


    private List<Point> snakeBody = new ArrayList<Point>();
    private GraphicsContext gc;

    private Point snakeHead;
    private Image foodImage,decoyImage;
    private PowerUp powerUp;
    private boolean gameOver = false, showDecoy = false, showPowerUp=false;
    private Point foodPoint,decoyPoint,powerUpPoint;
    private Direction currentDirecton = Direction.Down;

    private int levelIndex = 0;
    private Level currentLevel;

    private boolean shield = false;

    private int extraLives = 0;


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Anaconda");
//        stage.getIcons().add(new Image())
        Group root = new Group();
        Canvas canvas = new Canvas(width,height);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        gc = canvas.getGraphicsContext2D();
        currentLevel = GameData.levels[levelIndex];
        snakeBody = new ArrayList<>();
        score=0;
        gameOver=false;

        //before we start, we initialize our snake to 3 points. (3 parts).
        for(int i=0; i<3; i++){
            snakeBody.add(new Point(5,  rows /2));
        }

        //We initialize the snake head to the first part in the snake body list.
        snakeHead=snakeBody.get(0);

        generateFood();
        generateDecoy();
        generatePowerUp();

        startGameFrame(scene);
    }

    public void play(Stage stage, int level, int highestScore) throws Exception {
        levelIndex = level;
        this.highestScore = highestScore;
        start(stage);

    }

    private void run(Scene scene){
        if(gameOver){
            showScore();
            gc.setTextAlign(TextAlignment.JUSTIFY);
            if(score>= currentLevel.maxScore){
                gc.setFill(Color.GOLD);
                gc.setFont(new Font("Digital-8", 70));
                gc.fillText("You Won :)",width/3.5, height/2);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                levelIndex = levelIndex+1 <GameData.levels.length? ++levelIndex:0;
                game.stop();
                if(levelIndex==0){
                    gc.fillText("You finished game :)",width/3.5, height/2);
                }else{
                    try {
                        play(new Stage(),levelIndex, highestScore);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                return;
            }
            gc.setFill(Color.RED);
            gc.setFont(new Font("Digital-8", 70));
            gc.fillText("Game Over",width/3.5, height/2);
            game.stop();
            return;
        }

        listenToGameControl(scene);


        drawBackground();
        drawFood();
        drawSnake();
        drawObstacles();
        drawDecoy();
        drawPowerUp();
        showScore();

        for (int i = snakeBody.size()-1; i>=1; i--){
            snakeBody.get(i).x = snakeBody.get(i-1).x;
            snakeBody.get(i).y = snakeBody.get(i-1).y;
        }

        moveSnake(scene);

    }

    // Generation
    private void generateDecoy(){
        int x = 0;
        int y = 0;

        start:
        while (true){

            x = (int) (Math.random()*columns);
            y = (int)(Math.random()*rows);

            decoyPoint =  new Point(x,y);

            for (Point part:snakeBody){
                if(part.x == x && part.y == y){
                    continue start;
                }
                //We check to ensure that it's not collided with the obstacles
                for(Obstacle obstacle: currentLevel.sceneData.obstacles){
                    if(obstacle.point.x == x && obstacle.point.y == y){
                        continue start;
                    }
                }
                //We check to ensure that it's not collided with the food
                if(foodPoint.x == x && foodPoint.y ==y ){
                    continue start;
                }
            }

            final String imgName = decoy_images[(int) (Math.random()*decoy_images.length)];
            decoyImage = new Image(getClass().getResourceAsStream("img/"+imgName));
            break;
        }




    }
    private void generatePowerUp(){
        int x = 0;
        int y = 0;

        start:
        while (true){

            x = (int) (Math.random()*columns);
            y = (int)(Math.random()*rows);

            powerUpPoint =  new Point(x,y);

            for (Point part:snakeBody){
                if(part.x == x && part.y == y){
                    continue start;
                }
                //We check to ensure that it's not collided with the obstacles
                for(Obstacle obstacle: currentLevel.sceneData.obstacles){
                    if(obstacle.point.x == x && obstacle.point.y == y){
                        continue start;
                    }
                }
                //We check to ensure that it's not collided with the food
                if(foodPoint.x == x && foodPoint.y ==y ){
                    continue start;
                }
                if(decoyPoint.x == x && decoyPoint.y ==y && showDecoy){
                    continue start;
                }
            }

            final Power power = Power.values()[(int)(Math.random()*Power.values().length)];
            powerUp = new PowerUp(power);
            break;
        }




    }

    private void generateFood(){
        // we use a while-true loop (forever loop) to always, check and replace the food.
        // we alias the loop as "start".
        // once the food is eaten, we loop again (continue the loop), else, it remains that way.

        int foodX =0;
        int foodY = 0;


        start:
        while (true){
            //we generate random coordinate to place food.
            foodX = (int)(Math.random() * rows);
            foodY = (int)(Math.random()*columns);

            foodPoint = new Point(foodX, foodY);

            for(Point snake:snakeBody){
                if(snake.x == foodPoint.x && snake.y == foodPoint.y){
                    //if any part of the snake touched the food (eaten), we immediately stop the loop and re-position the food.
                    // This is a safe assumption, since the snake will be moving in linear motion (head-first motion).
                    continue start;
                }
                /// Inorder to prevent the food from generating where an obstacle is
                for(final Obstacle obstacle:currentLevel.sceneData.obstacles){
                    if(obstacle.point.x == foodX && obstacle.point.y == foodY){
                        continue start;
                    }
                }
            }

            //if no part of the snake touches the food, then that means the food can stay.
            final String imgName = food_images[(int) (Math.random()*food_images.length)];
            foodImage = new Image(getClass().getResourceAsStream("img/"+imgName));

            break;

        }
    }

    // displays
    private void drawBackground(){
        //This is to draw checkered green background.
        for(int x =0; x<rows; x++){
            //for each row, we check each column, if it's even, the background is light green else dark green.
            for(int y=0; y<columns; y++){
                //this is to check whether the position of the square is even or odd.
                //we add both x and y, to determine its position.
                // first row, x = 0: first column, y=0, therefore, 0+0 === 0 % 2 = 0, then dark green.
                // first row, x=0, second column, y=1, therefore, 0+1 === 1 % 2 = 1, then light green.
                // second row, y=1, first column, y=0, therefore 1+0 === 1 % 2  = 1, then light green. (Remember the first column on the first row was light green

                gc.setFill((y+x)%2 == 0? currentLevel.secondaryTileColor:currentLevel.tileColor);
                gc.fillRect(x*square_size /* at point of painting on x-axis */,
                        y*square_size /* at point of painting on y-axis */,
                        square_size /* width of painting */,
                        square_size /* height of painting */) ;
                // using same size for width and height of painting since we want a square.



            }

        }
    }
    private void drawObstacles(){

        for(Obstacle obstacle: currentLevel.sceneData.obstacles){
            final Point coordinate = obstacle.point;
            if(obstacle.tree){
                final Image tree = new Image(getClass().getResourceAsStream("img/tree.png"));
                gc.drawImage(tree,coordinate.x * square_size, coordinate.y * square_size, square_size,square_size);
            }
            else {
                gc.setFill(obstacle.secondaryColor);
                gc.fillRoundRect(coordinate.x * square_size, coordinate.y * square_size, square_size,square_size,15,15);
                gc.setFill(obstacle.color);
                gc.fillRoundRect(coordinate.x * square_size+2, coordinate.y * square_size+2, square_size-6,square_size-6,15,15);
            }

        }
    }
    private void drawFood(){
        gc.drawImage(foodImage, foodPoint.x*square_size, foodPoint.y*square_size, square_size,square_size);
    }
    private void drawDecoy(){

        if(showDecoy){
            gc.drawImage(decoyImage, decoyPoint.x*square_size, decoyPoint.y*square_size, square_size,square_size);
        }



    }
    private void drawPowerUp(){
        if(showPowerUp && powerUp!= null){
            gc.drawImage(powerUp.icon, powerUpPoint.x*square_size, powerUpPoint.y*square_size, square_size,square_size);
        }
    }

    private void drawSnake(){
        final Color color = !shield? Color.web("2196F3"):Color.WHITE;
        final Color color2 = !shield? Color.MIDNIGHTBLUE.desaturate():Color.WHITE;

        gc.setFill(color);
        gc.fillRoundRect(snakeHead.x * square_size, snakeHead.y * square_size, square_size-1, square_size-1, 35,35);
        gc.setFill(color2);
        gc.fillRoundRect(snakeHead.x*square_size+2, snakeHead.y * square_size+2,square_size-6,square_size-6,20,20);


        for(int i =0; i<snakeBody.size(); i++){
            final Point snakePart = snakeBody.get(i);
            gc.setFill(color);
            gc.fillRoundRect(snakePart.x*square_size, snakePart.y * square_size,square_size,square_size,20,20);
            gc.setFill(color2);
            gc.fillRoundRect(snakePart.x*square_size+4, snakePart.y * square_size+4,square_size-6,square_size-6,20,20);

        }
    }

    private void showScore(){
        final Image scoreImage = new Image(getClass().getResourceAsStream("img/score.png"));
        final Image highScoreImage = new Image(getClass().getResourceAsStream("img/highscore.png"));
        final Image extraLivesImage = new Image(getClass().getResourceAsStream("img/hearts.png"));


        gc.setFill(Color.web("FFF"));
        gc.setFont(new Font("Digital-6", 30));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText(String.valueOf(score), 2.25*square_size ,.75*square_size,square_size);
        gc.fillText(String.valueOf(highestScore), 5.25*square_size ,.75*square_size,square_size);
        gc.fillText(String.valueOf(extraLives), 8.25*square_size ,.75*square_size,square_size);



        gc.drawImage(scoreImage,1*square_size, 0*square_size, square_size,square_size);
        gc.drawImage(highScoreImage,4*square_size, 0*square_size, square_size,square_size);
        gc.drawImage(extraLivesImage,7*square_size, 0*square_size, square_size,square_size);




    }

    /// movements
    private void moveSnake(Scene scene){
        switch (currentDirecton){
            case Up -> moveUp();
            case Left -> moveLeft();
            case Down -> moveDown();
            case Right -> moveRight();
        }
        gameOver();
        eatFood();
        eatPowerUp(scene);
    }
    private void moveUp(){
        snakeHead.y--;
    }
    private void moveDown(){
        snakeHead.y++;
    }
    private void moveLeft(){
        snakeHead.x--;
    }
    private void moveRight(){
        snakeHead.x++;
    }

    // Game Mechanisms
    private void startGameFrame(Scene scene){
        game = new Timeline(new KeyFrame(Duration.millis(150), e-> run(scene)));
        game.setCycleCount(Animation.INDEFINITE);
        game.play();
    }
    private void gameOver(){

        //if the snake touches ANY OF the borders
        boolean touchesBorder = snakeHead.x *square_size >= width
                || snakeHead.y *square_size >=height
                || snakeHead.x <0
                || snakeHead.y<0;

        boolean touchesObstacle = false;
        for(int i = 1; i<currentLevel.sceneData.obstacles.length; i++){
            final Point obstaclePoint = currentLevel.sceneData.obstacles[i].point;
            if(snakeHead.x == obstaclePoint.x && snakeHead.y == obstaclePoint.y){
                touchesObstacle = true;
                break;
            }
        }

        // if the snake touches a decoy
        boolean touchesDecoy = decoyPoint.x == snakeHead.x && decoyPoint.y == snakeHead.y && showDecoy;

        // Or if the snake kills itself.
        boolean killsItself = false;

        for(int i = 1; i<snakeBody.size(); i++){
            final Point snakePart = snakeBody.get(i);
            if(snakeHead.x == snakePart.x && snakeHead.y == snakePart.y){
                killsItself = true;
                break;
            }
        }

        if(shield && touchesObstacle){
            touchesObstacle = false;
            disableShield();
            respawn();
        }

        if(shield && touchesDecoy){
            touchesDecoy = false;
            disableShield();
            respawn();
        }
        if(extraLives>=1){
            if(touchesObstacle && !shield){
                touchesObstacle = false;
                removeExtraLife();
                respawn();

            }
            if(touchesDecoy && !shield){
                touchesDecoy = false;
                removeExtraLife();
                respawn();

            }
            if(touchesBorder){
                touchesBorder = false;
                removeExtraLife();
                respawn();

            }
            if(killsItself){
                killsItself = false;
                removeExtraLife();
                respawn();
            }




        }




        //Then it will be game over whether the snake touches the border, obstacle, decoy or eats itself.
        gameOver = touchesBorder || killsItself || touchesObstacle || touchesDecoy;

        if(touchesBorder || touchesObstacle){
//            MediaPlayer mp =
        }



    }
    private void listenToGameControl(Scene scene){

        scene.setOnKeyPressed(keyEvent -> {
            KeyCode code = keyEvent.getCode();

            if(code==KeyCode.UP || code==KeyCode.W){
                // snake should move up.
                if(currentDirecton != Direction.Down){
                    currentDirecton = Direction.Up;
                }
            }
            else if(code==KeyCode.LEFT || code==KeyCode.A){
                // snake should move left.
                if(currentDirecton != Direction.Right){
                    currentDirecton = Direction.Left;
                }
            }
            else if(code==KeyCode.RIGHT|| code==KeyCode.D){
                // snake should move up.
                // snake should move left.
                if(currentDirecton != Direction.Left){
                    currentDirecton = Direction.Right;
                }
            }
            else if(code == KeyCode.ESCAPE){
                game.pause();
            }
            else if(code==KeyCode.P){
                game.play();
            }
            //snake should go down
            // snake should move left.
            else if(currentDirecton != Direction.Up){
                currentDirecton = Direction.Down;
            }


        });

    }

    // power-ups and actions
    private void reduceSpeed(Scene scene){
        final KeyFrame slowFrame = new KeyFrame(Duration.millis(175),e->run(scene));
        final KeyFrame fastFrame = new KeyFrame(Duration.millis(150),e->run(scene));
        final Timer timer = new Timer();

        // slow down the speed of the snake
        game.stop();
        game = new Timeline(slowFrame);
        game.setCycleCount(Animation.INDEFINITE);
        game.play();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //increase it after 5 seconds
                game.stop();
                game = new Timeline(fastFrame);
                game.setCycleCount(Animation.INDEFINITE);
                game.play();
            }
        }, 5000);

    }
    private void reduceBody(){
        snakeBody.removeLast();
    }

    private void addExtraLife(){
        extraLives+=1;
    }
    private void removeExtraLife(){
        extraLives = extraLives==0?0:--extraLives;
    }

    private void enableShield(){
        shield=true;
    }
    private void disableShield(){
        shield=false;
    }
    private void eatFood(){
        final int maxScore = currentLevel.maxScore;
        if(snakeHead.x == foodPoint.x && snakeHead.y == foodPoint.y){
            snakeBody.add(new Point(-1,-1));


            showDecoy = (int)Math.ceil((Math.random()*4)) == 1;
            showPowerUp = (int)Math.ceil((Math.random()*4)) == 1;


            generateFood();
            generateDecoy();
            generatePowerUp();

            score+=5;
            highestScore= Math.max(score, highestScore);

            if(score>=maxScore){
                gameOver=true;
            }
        }
    }
    private void eatPowerUp(Scene scene){
        if(snakeHead.x == powerUpPoint.x && snakeHead.y == powerUpPoint.y && showPowerUp){
            switch (powerUp.power){
                case Body -> reduceBody();
                case Life -> addExtraLife();
                case Speed -> reduceSpeed(scene);
                case Shield -> enableShield();
            }
            generatePowerUp();
        }
    }

    private void respawn(){
        final List<Point> snake = new ArrayList<>();
        snakeHead = new Point(5,  rows /2);
        snake.add(snakeHead);
        for(final Point part:snakeBody){
            snake.add(new Point(-1,-1));
        }

        snakeBody = snake;



    }


    @Override
    public void stop() throws Exception {
        game.stop();
        super.stop();
    }
}
