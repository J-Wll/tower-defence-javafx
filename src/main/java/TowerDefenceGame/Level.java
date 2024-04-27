/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

import java.io.File;
import java.util.Arrays;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author user
 */
public class Level {

//    Width and height of the level in tiles
    private final int levelWidth = 18;
    private final int levelHeight = 10;
//    Tile size in pixels
    private final int tileSize = 64;
//    Stores all the tiles
    private Tile[][] tileGrid = new Tile[levelHeight][levelWidth];
//    Contains textures
    private final Textures dict = new Textures();
    private final int currentLevel = 1;
//    Array of monsters
    private final Monster[] monsters = new Monster[50 * currentLevel];
    private int spawnThreshold = 100;
    private int spawnCounter = 0;

    /**
     *
     * @return
     */
    public Tile[][] getTileGrid() {
        return tileGrid;
    }

    /**
     *
     * @param tg
     */
    public void setTileGrid(Tile[][] tg) {
        this.tileGrid = tg;
    }

    public int getTileSize() {
        return tileSize;
    }

    /**
     *
     */
    public Level() {
        for (int yTile = 0; yTile < tileGrid.length; yTile++) {
            for (int xTile = 0; xTile < tileGrid[0].length; xTile++) {
                tileGrid[yTile][xTile] = new Tile(xTile * tileSize, yTile * tileSize, tileSize, tileSize, 1, 0, dict);
            }
        }

        for (int i = 0; i < monsters.length; i++) {
            monsters[i] = new Monster(this);
        }
    }

    public void mobSpawner() {
        if (spawnCounter >= spawnThreshold) {
            spawnCounter = 0;
            System.out.println("test");
            for (Monster mon : monsters) {
//                Could do a random num or some other system here for making mobs with dif ids
                if (!mon.getAlive()) {
                    mon.spawn(-10);
                }
            }
        } else {
            spawnCounter += 1;
        }
    }

    /**
     *
     * @param gc
     */
    public void render(GraphicsContext gc) {
        mobSpawner();
        gc.setFill(Color.RED);
        gc.fillRect(100, 100, 100, 100);

        for (int yTile = 0; yTile < tileGrid.length; yTile++) {
            for (int xTile = 0; xTile < tileGrid[0].length; xTile++) {
                tileGrid[yTile][xTile].render(gc);
            }
        }

        for (Monster mon : monsters) {
            if (mon.getAlive() == true) {
                mon.render(gc);

            }
        }
    }

}
