/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author user
 */
public class Monster implements GameSubscriber {

    private double x, y, width, height, health;
    private int monID = -10;
    private Level level;
    private Tile[][] tileGrid;
    private Boolean alreadySpawned = false;
    private Boolean alive = false;

//    speed of movement, counter for movements, amount of moves that trigger direction check, direction states, current direction
    private double moveSpeed = 4;
    private double walkCounter = 0;
    private int directionCheck;
    private final int right = 0, left = 1, up = 2, down = 3;
    private int direction = right;

//    location relative to the tile grid
    private int xCord = 0;
    private int yCord = 0;

    private final GraphicsContext gc;
    private final GameStatePublisher gameManager = GameStatePublisher.getInstance();

    public Monster(Level level, GraphicsContext gc) {

        this.level = level;
        this.tileGrid = level.getTileGrid();
        this.directionCheck = level.getTileSize();
        this.gc = gc;
    }

    /**
     *
     */
    public void spawn(int monID) {
        for (int yTile = 0; yTile < tileGrid.length; yTile++) {
//            Second int is the column 0 - 17, 2 = road
            if (tileGrid[yTile][0].getGroundID() == Values.path) {
                x = tileGrid[yTile][0].getX();
                y = tileGrid[yTile][0].getY();
                yCord = yTile;
                width = height = level.getTileSize();
                this.monID = monID;
                this.alive = true;
                this.alreadySpawned = true;

            }

        }
    }

    private void positionLogic() {
//        increases cords to match with tile grid every time the movement hits the tilesize
        switch (direction) {
            case right:
                xCord += 1;
                break;
            case left:
                xCord -= 1;
                break;
            case up:
                yCord -= 1;
                break;
            case down:
                yCord += 1;
                break;
        }

        //        If it's at the end of the track
        try {
            if (tileGrid[yCord][xCord].getAirID() == Values.end) {
//                System.out.println("end                   " + this);
                alive = false;
                gameManager.decreaseHp(1);
                gameManager.unsubscribe(this);
                render();
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        if (!alive) {
            return;
        }

//        System.out.println("new tile" + " xCord: " + xCord + "  yCord: " + yCord);
        directionSwitch();

        walkCounter = 0;
    }

    private void directionSwitch() {
//            Changing direction logic
        try {
            if (direction != up && tileGrid[yCord + 1][xCord].getGroundID() == Values.path) {
                direction = down;
                return;
            }
        } catch (Exception e) {
            System.out.println("Down" + e);
        }
        try {
            if (direction != down && tileGrid[yCord - 1][xCord].getGroundID() == Values.path) {
                direction = up;
                return;
            }
        } catch (Exception e) {
            System.out.println("Up" + e);
        }
        try {
            if (direction != left && tileGrid[yCord][xCord + 1].getGroundID() == Values.path) {
                direction = right;
                return;
            }
        } catch (Exception e) {
            System.out.println("Right" + e);
        }
        try {
            if (direction != right && tileGrid[yCord][xCord - 1].getGroundID() == Values.path) {
                direction = left;
                return;
            }
        } catch (Exception e) {
            System.out.println("Left" + e);
        }
    }

    public void move() {

//        runs whenever run counter >= tilesize (64)
        if (walkCounter >= directionCheck) {
//            Split off due to being large, this func also calls directionCheck;
            positionLogic();
        }

        if (!alive) {
            return;
        }

//        Movement, runs every frame
        walkCounter += moveSpeed;
        switch (direction) {
            case right:
                x += moveSpeed;
                break;
            case left:
                x -= moveSpeed;
                break;
            case up:
                y -= moveSpeed;
                break;
            case down:
                y += moveSpeed;
                break;
        }
    }

    public void render() {
//            System.out.println(this);
        gc.drawImage(Textures.getText().get(monID), x, y, width, height);
        if (!alive) {
            gc.drawImage(Textures.getText().get(Values.explode), x, y, width, height);
        }
    }

//    public void explode() {
////            System.out.println(this);
//    }
    @Override
    public void update(String event) {
        if ("lowHP".equals(event)) {

        }
        if ("slow-power".equals(event)) {
            moveSpeed *= 0.25;
        }
    }

    public Boolean getAlive() {
        return alive;
    }

    public Boolean getAlreadySpawned() {
        return alreadySpawned;
    }

}
