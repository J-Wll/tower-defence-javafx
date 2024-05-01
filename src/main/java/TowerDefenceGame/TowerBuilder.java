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
public class TowerBuilder {

    private double baseDamage, damage;
    private int cooldownCounter, attackRadius, maximumTargets, attackWidth, value, cost, attackCooldown;
    private Color attackColor;
    private String special;

    /**
     *
     */
    public TowerBuilder() {
        // Default values
        this.baseDamage = 0.04;
        this.attackCooldown = 1;
        this.attackRadius = 200;
        this.maximumTargets = 1;
        this.attackColor = Color.RED; // Default color
        this.attackWidth = 3;
        this.special = "";
        this.value = Values.laserTower;
        this.cost = 25;
    }

    /**
     *
     * @param baseDamage
     * @return
     */
    public TowerBuilder withBaseDamage(double baseDamage) {
        this.baseDamage = baseDamage;
        return this;
    }

    /**
     *
     * @param attackCooldown
     * @return
     */
    public TowerBuilder withAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
        return this;
    }

    /**
     *
     * @param attackRadius
     * @return
     */
    public TowerBuilder withAttackRadius(int attackRadius) {
        this.attackRadius = attackRadius;
        return this;
    }

    /**
     *
     * @param maximumTargets
     * @return
     */
    public TowerBuilder withMaximumTargets(int maximumTargets) {
        this.maximumTargets = maximumTargets;
        return this;
    }

    /**
     *
     * @param attackColor
     * @return
     */
    public TowerBuilder withAttackColor(Color attackColor) {
        this.attackColor = attackColor;
        return this;
    }

    /**
     *
     * @param attackWidth
     * @return
     */
    public TowerBuilder withAttackWidth(int attackWidth) {
        this.attackWidth = attackWidth;
        return this;
    }

    /**
     *
     * @param special
     * @return
     */
    public TowerBuilder withSpecial(String special) {
        this.special = special;
        return this;
    }

    /**
     *
     * @param value
     * @return
     */
    public TowerBuilder withValue(int value) {
        this.value = value;
        return this;
    }

    /**
     *
     * @param cost
     * @return
     */
    public TowerBuilder withCost(int cost) {
        this.cost = cost;
        return this;
    }

    /**
     *
     * @return
     */
    public Tower getTower() {
        return new Tower(baseDamage, value, cost, attackCooldown, attackRadius, maximumTargets, attackWidth, attackColor, special);
    }
}
