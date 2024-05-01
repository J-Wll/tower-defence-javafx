package TowerDefenceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author user
 */
public class Tower implements GameSubscriber {

    private int x, y;

    //    attackcool down is in 1/60th seconds (frames)
    private double baseDamage, damage;
    private int cooldownCounter, attackRadius, maximumTargets, attackWidth, value, cost, attackCooldown;
    private String special;
    private Color attackColour;
    private GameWindow gameWindow = GameWindow.getInstance();
    private GameStatePublisher gameManager = GameStatePublisher.getInstance();
    private GraphicsContext gc = gameWindow.getGc();

    private Boolean dmgBoostActive = false;

    private int boostCounter = 0;

    /**
     *
     * @param baseDamage
     * @param value the value of value
     * @param cost the value of cost
     * @param attackCooldown
     * @param attackRadius
     * @param maximumTargets
     * @param attackWidth the value of attackWidth
     * @param attackColour the value of attackColour
     * @param special the value of special
     */
    public Tower(double baseDamage, int value, int cost, int attackCooldown, int attackRadius, int maximumTargets, int attackWidth, Color attackColour, String special) {
        this.baseDamage = baseDamage;
        this.damage = baseDamage;
        this.attackCooldown = attackCooldown;
        this.cooldownCounter = attackCooldown;
        this.attackRadius = attackRadius;
        this.maximumTargets = maximumTargets;
        this.attackColour = attackColour;
        this.attackWidth = attackWidth;
        this.cost = cost;
        this.special = special;
        this.value = value;
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
            boostCounter = 0;
        }
//        Temporary attack boost
        if ("lowHP".equals(event)) {
            damage *= 1.5;
            attackCooldown *= 0.75;
            dmgBoostActive = true;
            boostCounter = 0;
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

                    if (targetCounter < maximumTargets && monX > xStart && monX < xEnd && monY > yStart && monY < yEnd) {

                        gc.setStroke(attackColour);
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
        return value;
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

    public int getCost() {
        return this.cost;
    }
}
