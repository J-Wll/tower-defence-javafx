/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
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
    private int monstersRemaining = monsters.length;

//    How often new monsters spawn and the counter for the logic. Starts at the same value so one spawns right away
    private int baseSpawnThreshold = 100;
    private int spawnThreshold = baseSpawnThreshold - currentLevel;
    private int spawnCounter = spawnThreshold;
//    Intensity value makes monsters spawn faster and makes them harder types
    private double intensityValue = 1 + currentLevel;
    private int lastUpdateIntensity = 0;
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

    /**
     *
     * @return
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     *
     * @param by
     */
    public void decreaseRemaining(int by) {
        monstersRemaining -= by;
        gameManager.decreaseRemaining(by);

    }

    /**
     *
     * @param gc
     * @param textures
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

        gameManager.setRemaining(monstersRemaining);
    }

    /**
     *
     */
    public void mobSpawner() {
        if (spawnCounter >= spawnThreshold) {
            spawnCounter = 0;
            for (int i = 0; i < monsters.length; i++) {
                Monster mon = monsters[i];
//                Could do a random num or some other system here for making mobs with dif ids
                if (!mon.getAlive() && !mon.getAlreadySpawned()) {
                    int toSpawn = Values.monster1;
//                    boss at the end
                    if (i >= monsters.length - 1) {
                        toSpawn = Values.monster4;
                    } else {
//                        Random system so sometimes fast/tough enemies are spawned
                        Random rand = new Random();
                        int randomMon = rand.nextInt(100 - 1 + 1) + 1;
                        if (randomMon >= 0 && randomMon <= 5 + Math.round(intensityValue)) {
                            toSpawn = Values.monster2;
                        } else if (randomMon >= 60 && randomMon <= 65 + Math.round(intensityValue)) {
                            toSpawn = Values.monster3;
                        }
                    }
                    mon.spawn(toSpawn);

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
     */
    public void render() {
//        System.out.println(intensityValue + " intensity " + Math.round(intensityValue));
        intensityValue += 0.004;
        int roundedIntensity = (int) Math.round(intensityValue);
        spawnThreshold = (int) (baseSpawnThreshold - currentLevel - Math.round(intensityValue) - roundedIntensity);
//        stop it from calling every frame
        if (roundedIntensity != lastUpdateIntensity) {
            gameManager.setIntensity(roundedIntensity);
            lastUpdateIntensity = roundedIntensity;
        }
        mobSpawner();
        gc.setFill(Color.RED);
        gc.fillRect(100, 100, 100, 100);

        for (int yTile = 0; yTile < tileGrid.length; yTile++) {
            for (int xTile = 0; xTile < tileGrid[0].length; xTile++) {
                tileGrid[yTile][xTile].render(gc);

            }
        }

//        Renders an extra time so towers, their attacks and bounding box (in debug) render on top
        for (int yTile = 0; yTile < tileGrid.length; yTile++) {
            for (int xTile = 0; xTile < tileGrid[0].length; xTile++) {
                if (tileGrid[yTile][xTile].getTower() != null) {
                    Tile currentTile = tileGrid[yTile][xTile];
                    tileGrid[yTile][xTile].render(gc);
                    currentTile.getTower().attack(monsters);
                }
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
