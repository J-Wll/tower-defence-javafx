/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author user
 */
public class Tile extends Rectangle {

    private int x, y, width, height, groundID, airID;

    public Tile(int x, int y, int width, int height, int groundID, int airID) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.groundID = groundID;
        this.airID = airID;
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillRect(x * 2, y, width, height);

        Image image = new Image("/grassTexture.png");
        gc.drawImage(image, x, y, width, height);
    }

}
