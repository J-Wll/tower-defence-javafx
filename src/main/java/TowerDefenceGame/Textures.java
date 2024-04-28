/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

import java.util.HashMap;
import javafx.scene.image.Image;

/**
 *
 * @author user
 */
public class Textures {

    private static HashMap<Integer, Image> dict = new HashMap<>();

    /**
     *
     */
    public Textures() {
        dict.put(Values.grass, new Image("/grassTexture.png"));
        dict.put(Values.path, new Image("/stoneTexture.png"));

        dict.put(Values.start, new Image("/startFlagAir.png"));
        dict.put(Values.end, new Image("/endFlagAir.png"));
        dict.put(Values.monster1, new Image("/monsterTexture1.png"));
        dict.put(Values.explode, new Image("/explode.png"));
    }

    /**
     *
     * @return
     */
    public static HashMap<Integer, Image> getText() {
        return dict;
    }

//    base layer counts up
    /**
     *
     */
    public int grassID = 1;

//    layer 2 counts down
    /**
     *
     */
    public int emptyID = -1;
}
