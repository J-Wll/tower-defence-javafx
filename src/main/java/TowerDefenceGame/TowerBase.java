package TowerDefenceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author user
 */
public class TowerBase implements GameSubscriber, Tower {

    private int x, y;

    private int baseDamage;
    private int damage;
    private int baseAttackSpeed;
    private int attackSpeed;
    private int attackRadius = 160;

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
    public void attack(Monster[] monsters) {
        for (Monster mon : monsters) {
            if (mon.getAlive()) {
//                Bounds of targeting
                int xStart = (x - (attackRadius / 2) - (Values.tileSize / 2));
                int xEnd = x + attackRadius / 2 + Values.tileSize / 2;
                int yStart = (y - (attackRadius / 2) - (Values.tileSize / 2));
                int yEnd = y + attackRadius / 2 + Values.tileSize / 2;

                double monX = mon.getX();
                double monY = mon.getY();

//                System.out.println("X: " + monX + " " + xStart + " " + xEnd + "\n" + "Y: " + monY + " " + yStart + " " + yEnd);
                if (monX > xStart && monX < xEnd && monY > yStart && monY < yEnd) {
                    System.out.println("inside");
                }
            }
        }
    }

    @Override
    public int getValue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getAttackRadius() {
        return attackRadius;
    }

    @Override
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
