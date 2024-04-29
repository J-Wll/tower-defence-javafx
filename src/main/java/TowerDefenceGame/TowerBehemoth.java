/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

/**
 *
 * @author user
 */
public class TowerBehemoth implements Tower {

    private int x, y;
    private final int maximumTargets = 5;
    private final double baseDamage = 0.06;
    private final int attackCooldown = 1;

    private final TowerBase baseTower;
    private int attackRadius = 250;

    /**
     *
     */
    public TowerBehemoth() {
        this.baseTower = new TowerBase(baseDamage, attackCooldown, attackRadius, maximumTargets);
        baseTower.setAttackWidth(5);
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
        return Values.behemothTower;
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
