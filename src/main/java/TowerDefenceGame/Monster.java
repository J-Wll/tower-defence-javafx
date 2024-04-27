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

    private int x, y, width, height;
    private int monID = -10;
    private Level level;
    private Tile[][] tileGrid;
    private Boolean alive = false;

//    speed of movement, counter for movements, amount of moves that trigger direction check, direction states, current direction
    private int moveSpeed = 2;
    private int walkCounter = 0;
    private int directionCheck;
    private final int right = 0, left = 1, up = 2, down = 3;
    private int direction = right;
//    location relative to the tile grid
    private int xCord = 0;
    private int yCord = 0;
    private boolean movedUp, movedDown, movedLeft, movedRight;

    public Monster(Level level) {

        this.level = level;
        this.tileGrid = level.getTileGrid();
        this.directionCheck = level.getTileSize();
        movedUp = movedDown = movedLeft = false;
        movedRight = true;

    }

    /**
     *
     */
    public void spawn(int monID) {
        for (int yTile = 0; yTile < tileGrid.length; yTile++) {
//            Second int is the column 0 - 17, 2 = road
            if (tileGrid[yTile][0].getGroundID() == 2) {
                x = tileGrid[yTile][0].getX();
                y = tileGrid[yTile][0].getY();
                yCord = yTile;
                width = height = level.getTileSize();
                this.monID = monID;
                alive = true;
            }

        }
    }

    private void directionSwitch() {
//            Changing direction logic
        System.out.println(movedUp + " " + movedDown + " " + movedLeft + " " + movedRight);
        try {
            if (!movedUp && tileGrid[yCord + 1][xCord].getGroundID() == 2) {
                System.out.println("Down");
                direction = down;
                movedUp = movedLeft = movedRight = false;
                movedDown = true;
                return;
            }
        } catch (Exception e) {
            System.out.println("Down" + e);
        }
        try {
            if (!movedDown && tileGrid[yCord - 1][xCord].getGroundID() == 2) {
                System.out.println("Up");
                direction = up;
                movedDown = movedLeft = movedRight = false;
                movedUp = true;
                return;
            }
        } catch (Exception e) {
            System.out.println("Up" + e);
        }
        System.out.println("right con: " + tileGrid[8][2].getGroundID());
        try {
            if (!movedLeft && tileGrid[yCord][xCord + 1].getGroundID() == 2) {
                System.out.println("Right");
                direction = right;
                movedDown = movedLeft = movedUp = false;
                movedRight = true;
                return;
            }
        } catch (Exception e) {
            System.out.println("Right" + e);
        }
        try {
            if (!movedRight && tileGrid[yCord][xCord - 1].getGroundID() == 2) {
                System.out.println("Left");
                direction = left;
                movedDown = movedRight = movedUp = false;
                movedLeft = true;
                return;
            }
        } catch (Exception e) {
            System.out.println("Left" + e);
        }
    }

    public void move() {

//        increases cords to match with tile grid every time the movement hits the tilesize
        if (walkCounter >= directionCheck) {
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

            if (xCord < 0) {
                xCord = 0;
            }
            if (yCord < 0) {
                yCord = 0;
            }

            System.out.println("new tile" + " xCord: " + xCord + "  yCord: " + yCord);

            directionSwitch();

            walkCounter = 0;

        }

        walkCounter += moveSpeed;
//        moving
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

    public void render(GraphicsContext gc) {
        gc.drawImage(Textures.getText().get(monID), x, y, width, height);
    }

    @Override
    public void update(String event) {
        if ("lowHP".equals(event)) {

        }
    }

    public Boolean getAlive() {
        return alive;
    }

}
