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

    private int x, y;

    private final Tower baseTower;
    private int attackRadius = 128;

    public TowerFlame(Tower baseTower) {
        this.baseTower = baseTower;
    }

    @Override
    public void attack(Monster[] monsters) {
        baseTower.attack(monsters);
    }

    @Override
    public int getValue() {
        return Values.flameTower;
    }

    public void update(String event) {
        baseTower.update(event);
    }

    public int getAttackRadius() {
        return attackRadius;
    }

    public void setPos(int x, int y) {
//        this.x = x;
//        this.y = y;
        baseTower.setPos(x, y);
    }

}
