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
    private int rewardVal;
    private int monID = -10;
    private Level level;
    private Tile[][] tileGrid;
    private Boolean alreadySpawned = false;
    private Boolean alive = false;

//    speed of movement, counter for movements, amount of moves that trigger direction check, direction states, current direction
    private double moveSpeed = 1;
    private double walkCounter = 0;
    private int directionCheck;
    private final int right = 0, left = 1, up = 2, down = 3;
    private int direction = right;

//    For flametower, time in frames (60 per s)
    private int burnTime = 0;

//    location relative to the tile grid
    private int xCord = 0;
    private int yCord = 0;

    private final GraphicsContext gc;
    private final GameStatePublisher gameManager = GameStatePublisher.getInstance();

    /**
     *
     * @param level
     * @param gc
     */
    public Monster(Level level, GraphicsContext gc) {

        this.level = level;
        this.tileGrid = level.getTileGrid();
        this.directionCheck = level.getTileSize();
        this.gc = gc;
    }

    /**
     *
     * @param monID
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

                switch (monID) {
                    case Values.monster1:
                        rewardVal = 3;
                        health = 10;
                        break;
                    case Values.monster2:
                        rewardVal = 5;
                        health = 10;
                        moveSpeed = 4;
                        break;
                    case Values.monster3:
                        rewardVal = 6;
                        health = 20;
                        break;
                    case Values.monster4:
                        rewardVal = 10;
                        health = 300;
                        moveSpeed = 0.5;
                        break;
                }
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

    /**
     *
     */
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

    /**
     *
     */
    public void render() {
        gc.drawImage(Textures.getText().get(monID), x, y, width, height);

        if (burnTime > 0 && alive) {
            gc.drawImage(Textures.getText().get(Values.onFire), x + 37, y, 25, 25);
            burnTime -= 1;
            takeDamage(0.01);
        }

        if (!alive) {
            gc.drawImage(Textures.getText().get(Values.explode), x, y, width, height);
        }
    }

//    public void explode() {
////            System.out.println(this);
//    }
    /**
     *
     * @param event
     */
    @Override
    public void update(String event) {
        if ("lowHP".equals(event)) {

        }
        if ("slow-power".equals(event)) {
            moveSpeed *= 0.25;
        }
    }

    /**
     *
     * @return
     */
    public Boolean getAlive() {
        return alive;
    }

    /**
     *
     * @return
     */
    public Boolean getAlreadySpawned() {
        return alreadySpawned;
    }

    /**
     *
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     *
     * @param health
     */
    public void setHealth(double health) {
        this.health = health;
        if (health <= 0) {
            alive = false;
            gameManager.increaseGold(rewardVal);
            render();
        }
    }

    /**
     *
     * @param damage
     */
    public void takeDamage(double damage) {
        setHealth(health - damage);
    }

    public void increaseBurnTime(int time) {
        this.burnTime += time;
    }
}
