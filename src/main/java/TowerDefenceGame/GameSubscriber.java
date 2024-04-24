package TowerDefenceGame;

// Interface for towers, enemies, etc. Subscribers will be notifed when events such as low hp are triggered.
public interface GameSubscriber {
    void update(String event);
}
