package TowerDefenceGame;

// Interface for towers, enemies, etc. Subscribers will be notifed when events such as low hp are triggered.

/**
 *
 * @author user
 */
public interface GameSubscriber {

    /**
     *
     * @param event
     */
    void update(String event);
}
