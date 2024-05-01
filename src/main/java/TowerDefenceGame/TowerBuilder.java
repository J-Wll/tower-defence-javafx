/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

import javafx.scene.paint.Color;

public class TowerBuilder {

    private double baseDamage, damage;
    private int cooldownCounter, attackRadius, maximumTargets, attackWidth, value, cost, attackCooldown;
    private Color attackColor;
    private String special;

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

    public TowerBuilder withBaseDamage(double baseDamage) {
        this.baseDamage = baseDamage;
        return this;
    }

    public TowerBuilder withAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
        return this;
    }

    public TowerBuilder withAttackRadius(int attackRadius) {
        this.attackRadius = attackRadius;
        return this;
    }

    public TowerBuilder withMaximumTargets(int maximumTargets) {
        this.maximumTargets = maximumTargets;
        return this;
    }

    public TowerBuilder withAttackColor(Color attackColor) {
        this.attackColor = attackColor;
        return this;
    }

    public TowerBuilder withAttackWidth(int attackWidth) {
        this.attackWidth = attackWidth;
        return this;
    }

    public TowerBuilder withSpecial(String special) {
        this.special = special;
        return this;
    }

    public TowerBuilder withValue(int value) {
        this.value = value;
        return this;
    }

    public TowerBuilder withCost(int cost) {
        this.cost = cost;
        return this;
    }

    public Tower getTower() {
        return new Tower(baseDamage, value, cost, attackCooldown, attackRadius, maximumTargets, attackWidth, attackColor, special);
    }
}
