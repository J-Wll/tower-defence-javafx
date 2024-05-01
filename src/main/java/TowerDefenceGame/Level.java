/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

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
    private int currentLevel;
//    Array of monsters
    private final Monster[] monsters;
//    one for testing
//    private final Monster[] monsters = new Monster[1];
    private int monstersRemaining;

//    How often new monsters spawn and the counter for the logic. Starts at the same value so one spawns right away
    private int baseSpawnThreshold = 90;
    private int spawnThreshold = baseSpawnThreshold - currentLevel;
    private int spawnCounter = spawnThreshold;
//    Intensity value makes monsters spawn faster and makes them harder types
    private double intensity = 1 + currentLevel;
    private int roundedIntensity = (int) Math.round(intensity);
    private int lastUpdateIntensity = 0;
    private final GraphicsContext gc;
    private final Textures textures;
    private final GameStatePublisher gameManager = GameStatePublisher.getInstance();

    /**
     *
     * @param gc
     * @param textures
     * @param currentLevel
     */
    public Level(GraphicsContext gc, Textures textures, int currentLevel) {
        this.gc = gc;
        this.textures = textures;
        this.currentLevel = currentLevel;
        monsters = new Monster[50 + (50 * currentLevel)];
        monstersRemaining = monsters.length;

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
        setIntensity(intensity + 0.30);
        monstersRemaining -= by;
        gameManager.decreaseRemaining(by);

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
//                        Starts at a low chance for hard enemies but scales pretty quick with intensity
                        Random rand = new Random();
                        int randomMon = rand.nextInt(100 - 1 + 1) + 1;
//                        2% chance for boss mob past 68
                        if (intensity > 68) {
                            if (randomMon == 1 || randomMon == 2) {
                                toSpawn = Values.monster4;
                            }
                        }
                        if (randomMon >= 3 && randomMon <= 4 + Math.round(intensity)) {
                            toSpawn = Values.monster2;
                        } else if (randomMon >= 60 && randomMon <= 61 + Math.round(intensity)) {
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
     * @param to
     */
    public void setIntensity(double to) {
        intensity = to;
        roundedIntensity = (int) Math.round(intensity);

        //        stop it from calling every frame when there's no change
        if (roundedIntensity != lastUpdateIntensity) {
            gameManager.setIntensity(roundedIntensity);
            lastUpdateIntensity = roundedIntensity;
        }
    }

    /**
     *
     */
    public void render() {
        setIntensity(intensity + 0.001 + ((currentLevel * 1.45) / 1000));

//        Maximum spawn rate of one per 2 frames (30 per second)
        if (spawnThreshold >= (3 + currentLevel)) {
            spawnThreshold = (int) (baseSpawnThreshold - currentLevel - roundedIntensity);
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
