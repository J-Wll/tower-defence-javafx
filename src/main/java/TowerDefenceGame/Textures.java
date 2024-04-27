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
        dict.put(1, new Image("/grassTexture.png"));
        dict.put(2, new Image("/stoneTexture.png"));

//        dict.put(-1, new Image("/emptyAir.png"));
        dict.put(-10, new Image("/monsterTexture1.png"));
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
