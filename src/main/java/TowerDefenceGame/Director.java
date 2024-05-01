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

    public void constructTowerFlame(TowerBuilder builder) {
        builder.withBaseDamage(0.01)
                .withAttackCooldown(1)
                .withAttackRadius(128)
                .withMaximumTargets(1)
                .withAttackColor(Color.ORANGE)
                .withAttackWidth(8)
                .withSpecial("flame")
                .withValue(Values.flameTower);

    }

    public void constructTowerBehemoth(TowerBuilder builder) {
        builder
                .withBaseDamage(0.06)
                .withAttackCooldown(1)
                .withAttackRadius(250)
                .withMaximumTargets(5)
                .withAttackWidth(5)
                .withValue(Values.behemothTower);

    }

    public void constructTowerLaser(TowerBuilder builder) {
        builder
                .withBaseDamage(0.04)
                .withAttackCooldown(1)
                .withAttackRadius(200)
                .withMaximumTargets(1)
                .withValue(Values.laserTower);
    }

    public void constructTowerShotgun(TowerBuilder builder) {
        builder
                .withBaseDamage(9)
                .withAttackCooldown(150)
                .withAttackRadius(128)
                .withMaximumTargets(4)
                .withAttackWidth(12)
                .withValue(Values.shotgunLaserTower);
    }

}
