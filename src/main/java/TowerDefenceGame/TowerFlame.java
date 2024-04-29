/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

import javafx.scene.paint.Color;

/**
 *
 * @author user
 */
public class TowerFlame implements Tower {

    private int x, y;
    private final int maximumTargets = 1;
    private final double baseDamage = 0.01;
    private final int attackCooldown = 1;
    private int attackRadius = 128;

    private final TowerBase baseTower;

    /**
     *
     */
    public TowerFlame() {
        this.baseTower = new TowerBase(baseDamage, attackCooldown, attackRadius, maximumTargets);
        baseTower.setAttackColor(Color.ORANGE);
        baseTower.setAttackWidth(8);
        baseTower.setSpecial("flame");
    }

    /**
     *
     * @param monsters
     */
    @Override
    public void attack(Monster[] monsters) {
        baseTower.attack(monsters);
    }

    /**
     *
     * @return
     */
    @Override
    public int getValue() {
        return Values.flameTower;
    }

    /**
     *
     * @param event
     */
    public void update(String event) {
        baseTower.update(event);
    }

    /**
     *
     * @return
     */
    public int getAttackRadius() {
        return attackRadius;
    }

    /**
     *
     * @param x
     * @param y
     */
    public void setPos(int x, int y) {
//        this.x = x;
//        this.y = y;
        baseTower.setPos(x, y);
    }

}
