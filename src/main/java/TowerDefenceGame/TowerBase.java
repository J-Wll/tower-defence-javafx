package TowerDefenceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author user
 */
public class TowerBase implements GameSubscriber, Tower {

    private int x, y, width, height, textID;

    private int baseDamage;
    private int damage;
    private int baseAttackSpeed;
    private int attackSpeed;

    /**
     *
     * @param event
     */
    @Override
    public void update(String event) {
//        Temporary attack boost
        if ("lowHP".equals(event)) {
            damage *= 1.5;
            attackSpeed *= 1.5;
        }
//        Reset attack
        if ("normalHP".equals(event)) {
            damage = baseDamage;
            attackSpeed = baseAttackSpeed;
        }
    }

    @Override
    public void attack() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getValue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
