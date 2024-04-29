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
    //    attackcool down is in 1/60th seconds (frames)
    private double baseAttackCooldown;
    private double attackCooldown;
    private int cooldownCounter = 0;
    private int attackRadius = 160;
    private int maximumTargets = 1;
    private int attackWidth = 3;
    private String special = null;
    private Color attackColor = Color.RED;
    private GameWindow gameWindow = GameWindow.getInstance();
    private GameStatePublisher gameManager = GameStatePublisher.getInstance();
    private GraphicsContext gc = gameWindow.getGc();

    private Boolean dmgBoostActive = false;

    private int boostCounter = 0;

    /**
     *
     * @param baseDamage
     * @param attackSpeed
     * @param attackRadius
     * @param maximumTargets
     */
    public TowerBase(double baseDamage, int attackCooldown, int attackRadius, int maximumTargets) {
        this.baseDamage = baseDamage;
        this.attackCooldown = attackCooldown;
        this.attackRadius = attackRadius;
        this.maximumTargets = maximumTargets;
        damage = baseDamage;
        gameManager.subscribe(this);
        this.cooldownCounter = attackCooldown;
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
            boostCounter = 0;
        }
//        Temporary attack boost
        if ("lowHP".equals(event)) {
            damage *= 1.5;
            attackCooldown *= 1.5;
        }
    }

    /**
     *
     * @param monsters
     */
    public void attack(Monster[] monsters) {
        int targetCounter = 0;
        boostCounter += 1;
        cooldownCounter += 1;
        if (dmgBoostActive) {
            if (boostCounter / 60 == 10) {
                dmgBoostActive = false;
                damage = baseDamage;
            }

        }
        if (cooldownCounter >= attackCooldown) {
            Boolean attacked = false;
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

                        gc.setStroke(attackColor);
                        gc.setLineWidth(attackWidth);
                        gc.strokeLine(x + tSize / 2, y + tSize / 2, monX + tSize / 2, monY + tSize / 2);
                        mon.takeDamage(damage);
                        targetCounter += 1;
                        attacked = true;

                        if ("flame".equals(special)) {
                            mon.increaseBurnTime(10);
                        }

                    }
                }
            }
            if (attacked) {
                cooldownCounter = 0;
                attacked = false;
            }
        }
    }

    /**
     *
     * @return
     */
    public int getValue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        this.x = x;
        this.y = y;
    }

    public void setAttackColor(Color attackColor) {
        this.attackColor = attackColor;
    }

    public void setAttackWidth(int attackWidth) {
        this.attackWidth = attackWidth;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

}
