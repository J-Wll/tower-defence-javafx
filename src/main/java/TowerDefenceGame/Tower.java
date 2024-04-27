package TowerDefenceGame;

/**
 *
 * @author user
 */
public class Tower implements GameSubscriber{
    private int baseDamage;
    private int damage;
    private int baseAttackSpeed;
    private int attackSpeed;
    
    /**
     *
     * @param event
     */
    public void update(String event){
//        Temporary attack boost
        if ("lowHP".equals(event)){
            damage *= 1.5;
            attackSpeed *= 1.5;
        }
//        Reset attack
        if ("normalHP".equals(event)){
            damage = baseDamage;
            attackSpeed = baseAttackSpeed;
        }
    }
}
