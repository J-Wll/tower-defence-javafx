/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author user
 */
public class Tile {

    private int x, y, width, height, groundID, airID;
    private Textures dict;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param groundID
     * @param airID
     * @param dict
     */
    public Tile(int x, int y, int width, int height, int groundID, int airID, Textures dict) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.groundID = groundID;
        this.airID = airID;
        this.dict = dict;
    }

    /**
     *
     * @param groundID
     */
    public void setGroundID(int groundID) {
        this.groundID = groundID;
    }

    public int getGroundID() {
        return groundID;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     *
     * @param airID
     */
    public void setAirID(int airID) {
        this.airID = airID;
    }

    /**
     *
     * @param gc
     */
    public void render(GraphicsContext gc) {
        gc.drawImage(Textures.getText().get(groundID), x, y, width, height);
        if (airID < Values.empty) {
            gc.drawImage(Textures.getText().get(airID), x, y, width, height);
        }
    }

}
