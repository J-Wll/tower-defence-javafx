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
public class Director {

    /**
     *
     * @param builder
     */
    public void constructTowerFlame(TowerBuilder builder) {
        builder.withBaseDamage(0.05)
                .withAttackCooldown(1)
                .withAttackRadius(128)
                .withMaximumTargets(1)
                .withAttackColor(Color.ORANGE)
                .withAttackWidth(8)
                .withSpecial("flame")
                .withCost(50)
                .withValue(Values.flameTower);

    }

    /**
     *
     * @param builder
     */
    public void constructTowerBehemoth(TowerBuilder builder) {
        builder
                .withBaseDamage(0.08)
                .withAttackCooldown(1)
                .withAttackRadius(250)
                .withMaximumTargets(5)
                .withAttackWidth(5)
                .withCost(100)
                .withValue(Values.behemothTower);

    }

    /**
     *
     * @param builder
     */
    public void constructTowerLaser(TowerBuilder builder) {
        builder
                .withBaseDamage(0.05)
                .withAttackCooldown(1)
                .withAttackRadius(200)
                .withMaximumTargets(1)
                .withCost(25)
                .withValue(Values.laserTower);
    }

    /**
     *
     * @param builder
     */
    public void constructTowerShotgun(TowerBuilder builder) {
        builder
                .withBaseDamage(9)
                .withAttackCooldown(150)
                .withAttackRadius(128)
                .withMaximumTargets(4)
                .withAttackWidth(12)
                .withCost(50)
                .withValue(Values.shotgunLaserTower);
    }

}
