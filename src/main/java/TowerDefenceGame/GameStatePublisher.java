package TowerDefenceGame;

import java.util.ArrayList;

// Publishes events based on the values of hp, gold

/**
 *
 * @author user
 */
public class GameStatePublisher {

    private static GameStatePublisher instance;
    private ArrayList<GameSubscriber> subscribers;
    private int hp;
    private int gold;

    private GameStatePublisher(ArrayList<GameSubscriber> subscribers, int hp, int gold) {
        this.subscribers = subscribers;
        this.hp = hp;
        this.gold = gold;
    }

    /**
     *
     * @param subs
     * @param hp
     * @param gold
     * @return
     */
    public static GameStatePublisher getInstance(ArrayList<GameSubscriber> subs, int hp, int gold) {
        if (instance == null) {
            instance = new GameStatePublisher(subs, hp, gold);
        }
        return instance;
    }

    /**
     *
     * @param s
     */
    public void subscribe(GameSubscriber s) {
        subscribers.add(s);
    }

    /**
     *
     * @param s
     */
    public void unsubscribe(GameSubscriber s) {
        int i = subscribers.indexOf(s);
        if (subscribers.indexOf(i) >= 0) {
            subscribers.remove(i);
        }
    }

    /**
     *
     * @param event
     */
    public void notify(String event) {
        for (GameSubscriber sub : subscribers) {
            sub.update(event);
        }
    }

    /**
     *
     * @return
     */
    public int getHp() {
        return hp;
    }

    /**
     *
     * @param hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     *
     * @return
     */
    public int getGold() {
        return gold;
    }

    /**
     *
     * @param gold
     */
    public void setGold(int gold) {
        this.gold = gold;
    }

}
