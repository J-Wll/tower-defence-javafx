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
    private final double baseDamage = 0.04;
    private final double attackSpeed = 5;

    private final TowerBase baseTower;
    private int attackRadius = 192;

    public TowerBehemoth() {
        this.baseTower = new TowerBase(baseDamage, attackSpeed, attackRadius, maximumTargets);
    }

    @Override
    public void attack(Monster[] monsters) {
        baseTower.attack(monsters);
    }

    @Override
    public int getValue() {
        return Values.behemothTower;
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
