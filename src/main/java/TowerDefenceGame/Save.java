/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

import java.io.*;
import java.util.*;

/**
 *
 * @author 1512369
 */
public class Save {

    public void loadSave(Level level, File loadPath) {
        Tile[][] tileGrid = level.getTileGrid();
        try {
            Scanner loadScanner = new Scanner(loadPath);
            while (loadScanner.hasNext()) {
                for (int yTile = 0; yTile < tileGrid.length; yTile++) {
                    for (int xTile = 0; xTile < tileGrid[0].length; xTile++) {
                        System.out.println(loadScanner.nextInt());
                        tileGrid[yTile][xTile].setGroundID(loadScanner.nextInt());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            System.out.println("loadSave");
        }
        level.setTileGrid(tileGrid);

    }

}
