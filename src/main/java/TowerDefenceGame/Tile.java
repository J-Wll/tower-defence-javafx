/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author user
 */
public class Tile {

    private int x, y, width, height, groundID, airID;
    private Textures dict;

    public Tile(int x, int y, int width, int height, int groundID, int airID, Textures dict) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.groundID = groundID;
        this.airID = airID;
        this.dict = dict;
    }

    public void setGroundID(int groundID) {
        this.groundID = groundID;
    }

    public void setAirID(int airID) {
        this.airID = airID;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(Textures.getText().get(groundID), x, y, width, height);
        if (airID < 0) {
            System.out.println("test");
            gc.drawImage(Textures.getText().get(airID), x, y, width, height);
        }
    }

}
