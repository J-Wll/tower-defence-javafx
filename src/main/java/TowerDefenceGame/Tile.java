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
public class Tile {

    private int x, y, width, height, groundID, airID;
    private Textures dict;
    private Tower tower;

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

    public int getAirID() {
        return airID;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setTower(Tower t) {
        this.tower = t;
        this.airID = t.getValue();
    }

    public Tower getTower() {
        return tower;
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
            if (Values.DEBUG_MODE && tower != null) {
                int attackRadius = tower.getAttackRadius();
                gc.setStroke(Color.BLACK);
                gc.strokeRect(x - attackRadius / 2, y - attackRadius / 2, width + attackRadius, height + attackRadius);
            }
        }
    }

}
