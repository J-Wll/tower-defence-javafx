package TowerDefenceGame;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.control.Label;

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

    private GameStatePublisher(int hp, int gold) {
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
    public static GameStatePublisher getInstance(int hp, int gold) {
        if (instance == null) {
            instance = new GameStatePublisher(hp, gold);
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

    public void render(Group renderTo) {
        Label goldLb = new Label("Gold: " + Integer.toString(gold));
        goldLb.getStyleClass().add("gold-lb");
        goldLb.setLayoutY(30);
        goldLb.setLayoutX(50);
        renderTo.getChildren().add(goldLb);

        Label hpLb = new Label("HP: " + Integer.toString(hp));
        hpLb.getStyleClass().add("hp-lb");
        hpLb.setLayoutY(30);
        hpLb.setLayoutX(1035);
        renderTo.getChildren().add(hpLb);
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
