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
    private double maxHealth;
    private int rewardVal = 10;
    private int hpLoss = 1;
    private int monID = -10;
    private Level level;
    private Tile[][] tileGrid;
    private Boolean alreadySpawned = false;
    private Boolean alive = false;

//    move speed has issues if it doesn't go into 64 that cleanly
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
                        rewardVal = 6;
                        health = 15;
                        moveSpeed = 2.5;
                        hpLoss = 2;
                        break;
                    case Values.monster3:
                        rewardVal = 10;
                        health = 40;
                        moveSpeed = 0.8;
                        hpLoss = 3;
                        break;
                    case Values.monster4:
                        rewardVal = 100;
                        health = 300;
                        moveSpeed = 0.5;
                        hpLoss = 10;
                        break;
                }
                this.maxHealth = health;
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
//        To keep it lined up with the track
        x = xCord * 64;
        y = yCord * 64;
        //        If it's at the end of the track
        try {
            if (tileGrid[yCord][xCord].getAirID() == Values.end) {
                alive = false;
                gameManager.decreaseHp(hpLoss);
                gameManager.decreaseRemaining(1);
                gameManager.unsubscribe(this);
                render();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        if (!alive) {
            return;
        }
        directionSwitch();
        walkCounter = 0;
    }

    private void directionSwitch() {
//            Changing direction logic
//            Needs to be in try catches because the array will be out of bounds when on edge
        try {
            if (direction != up && tileGrid[yCord + 1][xCord].getGroundID() == Values.path) {
                direction = down;
                return;
            }
        } catch (Exception e) {
        }
        try {
            if (direction != down && tileGrid[yCord - 1][xCord].getGroundID() == Values.path) {
                direction = up;
                return;
            }
        } catch (Exception e) {
        }
        try {
            if (direction != left && tileGrid[yCord][xCord + 1].getGroundID() == Values.path) {
                direction = right;
                return;
            }
        } catch (Exception e) {
        }
        try {
            if (direction != right && tileGrid[yCord][xCord - 1].getGroundID() == Values.path) {
                direction = left;
                return;
            }
        } catch (Exception e) {
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
//        health bar
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y - 10, width, 8);
        gc.setFill(Color.GREEN);
        gc.fillRect(x + 1, y - 9, ((health / maxHealth) * width) - 2, 6);
        gc.drawImage(Textures.getText().get(monID), x, y, width, height);

        if (burnTime > 0 && alive) {
            gc.drawImage(Textures.getText().get(Values.onFire), x + 37, y, 25, 25);
            burnTime -= 1;
            takeDamage(0.015);
        }

        if (!alive) {
            gc.drawImage(Textures.getText().get(Values.explode), x, y, width, height);
        }
    }

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
            level.decreaseRemaining(1);
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

    /**
     *
     * @param time
     */
    public void increaseBurnTime(int time) {
        this.burnTime += time;
    }
}
