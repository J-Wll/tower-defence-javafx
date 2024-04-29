package TowerDefenceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author user
 */
public class TowerBase implements GameSubscriber {

    private int x, y;

//    constant firing laser so v low damage
    private double baseDamage = 0.04;
    private double damage = baseDamage;
    private double baseAttackSpeed;
    private double attackSpeed;
    private int attackRadius = 160;
    private int maximumTargets = 1;
    private GameWindow gameWindow = GameWindow.getInstance();
    private GameStatePublisher gameManager = GameStatePublisher.getInstance();
    private GraphicsContext gc = gameWindow.getGc();

    private Boolean dmgBoostActive = false;

    private int timeCounter = 0;

    public TowerBase(double baseDamage, double attackSpeed, int attackRadius, int maximumTargets) {
        this.baseDamage = baseDamage;
        this.attackSpeed = attackSpeed;
        this.attackRadius = attackRadius;
        this.maximumTargets = maximumTargets;
        damage = baseDamage;
        gameManager.subscribe(this);
    }

    /**
     *
     * @param event
     */
    @Override

    public void update(String event) {
        if ("damage-power".equals(event)) {
            damage *= 1.5;
            dmgBoostActive = true;
            timeCounter = 0;
        }
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

    public void attack(Monster[] monsters) {
        int targetCounter = 0;
        System.out.println(timeCounter += 1);
        System.out.println(timeCounter / 60);
        if (dmgBoostActive) {
            if (timeCounter / 60 == 10) {
                dmgBoostActive = false;
                damage = baseDamage;
            }

        }
        for (Monster mon : monsters) {
            if (mon.getAlive()) {
//                Bounds of targeting
                int tSize = Values.tileSize;
                int xStart = (x - (attackRadius / 2) - (tSize / 2));
                int xEnd = x + attackRadius / 2 + tSize / 2;
                int yStart = (y - (attackRadius / 2) - (tSize / 2));
                int yEnd = y + attackRadius / 2 + tSize / 2;

                double monX = mon.getX();
                double monY = mon.getY();

//                System.out.println("X: " + monX + " " + xStart + " " + xEnd + "\n" + "Y: " + monY + " " + yStart + " " + yEnd);
                if (targetCounter < maximumTargets && monX > xStart && monX < xEnd && monY > yStart && monY < yEnd) {

                    gc.setStroke(Color.RED);
                    gc.strokeLine(x + tSize / 2, y + tSize / 2, monX + tSize / 2, monY + tSize / 2);
                    mon.takeDamage(damage);
                    targetCounter += 1;
                }
            }
        }
    }

    public int getValue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getAttackRadius() {
        return attackRadius;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
