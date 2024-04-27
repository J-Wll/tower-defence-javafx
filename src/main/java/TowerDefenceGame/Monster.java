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

    private int x, y, width, height, textID;
    private Level level;

    public Monster(Level level) {
        this.level = level;
        spawn();
    }

    /**
     *
     */
    public void spawn() {
        Tile[][] tileGrid = level.getTileGrid();
        for (int yTile = 0; yTile < tileGrid.length; yTile++) {
//            Second int is the column 0 - 17, 2 = road
            if (tileGrid[yTile][0].getGroundID() == 2) {
                x = tileGrid[yTile][0].getX();
                y = tileGrid[yTile][0].getY();
                width = height = level.getTileSize();
            }

        }
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(Textures.getText().get(-10), x, y, width, height);
    }

    @Override
    public void update(String event) {
        if ("lowHP".equals(event)) {

        }
    }
}
