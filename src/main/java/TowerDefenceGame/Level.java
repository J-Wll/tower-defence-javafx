/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
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
    private final int currentLevel = 1;
//    Array of monsters
    private final Monster[] monsters = new Monster[50 + (50 * currentLevel)];
//    one for testing
//    private final Monster[] monsters = new Monster[1];
//    How often new monsters spawn and the counter for the logic. Starts at the same value so one spawns right away
    private int spawnThreshold = 100;
    private int spawnCounter = spawnThreshold;
    private final GraphicsContext gc;
    private final Textures textures;
    private final GameStatePublisher gameManager = GameStatePublisher.getInstance();

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
    public Level(GraphicsContext gc, Textures textures) {
        this.gc = gc;
        this.textures = textures;

        for (int yTile = 0; yTile < tileGrid.length; yTile++) {
            for (int xTile = 0; xTile < tileGrid[0].length; xTile++) {
                tileGrid[yTile][xTile] = new Tile(xTile * tileSize, yTile * tileSize, tileSize, tileSize, Values.grass, Values.empty, textures);
            }
        }

        for (int i = 0; i < monsters.length; i++) {
            monsters[i] = new Monster(this, gc);
        }
    }

    public void mobSpawner() {
        if (spawnCounter >= spawnThreshold) {
            spawnCounter = 0;
            for (Monster mon : monsters) {
//                Could do a random num or some other system here for making mobs with dif ids
                if (!mon.getAlive() && !mon.getAlreadySpawned()) {
                    mon.spawn(Values.monster1);
                    gameManager.subscribe(mon);
                    break;
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
    public void render() {
        mobSpawner();
        gc.setFill(Color.RED);
        gc.fillRect(100, 100, 100, 100);

        for (int yTile = 0; yTile < tileGrid.length; yTile++) {
            for (int xTile = 0; xTile < tileGrid[0].length; xTile++) {
                tileGrid[yTile][xTile].render(gc);
            }
        }

        for (Monster mon : monsters) {
            if (mon.getAlive()) {
                mon.move();
                mon.render();
            }
        }
    }

}
