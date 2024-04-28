/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

/**
 *
 * @author user
 */
// store of values so they are not hard coded
// tiles (ground) are > 0
// 0 is empty
// < 0 is the air layer, or anything rendering above the ground
public class Values {

    public static final int empty = 0;
    public static final int grass = 1;
    public static final int path = 2;
    public static final int start = -1;
    public static final int end = -2;
    public static final int laserTower = -3;
    public static final int flameTower = -4;
    public static final int behemothTower = -5;
    public static final int shotgunLaserTower = -6;
    public static final int monster1 = -20;
    public static final int explode = -30;

}

//        dict.put(1, new Image("/grassTexture.png"));
//        dict.put(2, new Image("/stoneTexture.png"));
//
//        dict.put(-1, new Image("/startFlagAir.png"));
//        dict.put(-2, new Image("/endFlagAir.png"));
//        dict.put(-10, new Image("/monsterTexture1.png"));
