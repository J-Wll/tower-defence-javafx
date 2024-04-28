/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

/**
 *
 * @author user
 */
public class TowerFlame implements Tower {

    private final Tower baseTower;

    public TowerFlame(Tower baseTower) {
        this.baseTower = baseTower;
    }

    @Override
    public void attack() {
        baseTower.attack();
    }

    @Override
    public int getValue() {
        return Values.flameTower;
    }

}
