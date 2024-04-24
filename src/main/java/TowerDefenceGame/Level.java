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
public class Level {

//    Width and height of the level in blocks
    private int levelWidth = 12;
    private int levelHeight = 8;
//    Block size in pixels
    private int blockSize = 64;
//    Stores all the blocks
    private Block[][] blockGrid = new Block[levelHeight][levelWidth];

    public Level() {
        for (int wi = 0; wi < blockGrid.length; wi++) {
            for (int he = 0; he < blockGrid[0].length; he++) {
                blockGrid[wi][he] = new Block(he * blockSize, wi * blockSize, blockSize, blockSize, 0, 0);
            }
        }
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(100, 100, 100, 100);

        for (int he = 0; he < blockGrid.length; he++) {
            for (int wi = 0; wi < blockGrid[0].length; wi++) {
                blockGrid[he][wi].render(gc);
            }
        }
    }

}
