package TowerDefenceGame;

import java.util.ArrayList;

// Publishes events based on the values of hp, gold
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

    public static GameStatePublisher getInstance(ArrayList<GameSubscriber> subs, int hp, int gold) {
        if (instance == null) {
            instance = new GameStatePublisher(subs, hp, gold);
        }
        return instance;
    }

    public void subscribe(GameSubscriber s) {
        subscribers.add(s);
    }

    public void unsubscribe(GameSubscriber s) {
        int i = subscribers.indexOf(s);
        if (subscribers.indexOf(i) >= 0) {
            subscribers.remove(i);
        }
    }

    public void notify(String event) {
        for (GameSubscriber sub : subscribers) {
            sub.update(event);
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

}
