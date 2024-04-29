package TowerDefenceGame;

import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
    private IntegerProperty hp;
    private IntegerProperty gold;

    private GameStatePublisher(int hp, int gold) {
        this.hp = new SimpleIntegerProperty(hp);
        this.gold = new SimpleIntegerProperty(gold);
        this.subscribers = new ArrayList();
    }

    /**
     *
     * @param hp
     * @param gold
     * @return
     */
//    Version intended for creating it
    public static GameStatePublisher getInstance(int hp, int gold) {
        if (instance == null) {
            instance = new GameStatePublisher(hp, gold);
        }
        return instance;
    }

//    Version for acessing without args
    /**
     *
     * @return
     */
    public static GameStatePublisher getInstance() {
        return instance;
    }

    public static void deleteInstance() {
        instance = null;
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
     * @param renderTo
     */
    public void render(Group renderTo) {
        int rY = 645;
        Label goldLb = new Label();
        StringBinding goldTextBinding = Bindings.createStringBinding(
                () -> "Gold: " + gold.get(),
                gold
        );
        goldLb.textProperty().bind(goldTextBinding);
        goldLb.getStyleClass().add("gold-lb");
        goldLb.setLayoutY(rY);
        goldLb.setLayoutX(25);
        renderTo.getChildren().add(goldLb);

        Label hpLb = new Label();
        StringBinding hpTextBinding = Bindings.createStringBinding(
                () -> "HP: " + hp.get(),
                hp
        );
        hpLb.textProperty().bind(hpTextBinding);
        hpLb.getStyleClass().add("hp-lb");
        hpLb.setLayoutY(rY);
        hpLb.setLayoutX(1060);
        renderTo.getChildren().add(hpLb);

        Label levelLb = new Label("Current level: ");
        Label intensityLb = new Label("Intensity factor: ");
        Label remainingLb = new Label("Enemies remaining: ");
    }

    /**
     *
     * @return
     */
    public int getHp() {
        return hp.get();
    }

    /**
     *
     * @param hp
     */
    public void setHp(int hp) {
        this.hp.set(hp);

    }

    /**
     *
     * @param damage
     */
    public void decreaseHp(int damage) {
//        calls sethp in case want to validate in there
        setHp(hp.get() - damage);
    }

    /**
     *
     * @param increase
     */
    public void increaseHp(int increase) {
        setHp(hp.get() + increase);
    }

    /**
     *
     * @return
     */
    public int getGold() {
        return gold.get();
    }

    /**
     *
     * @param gold
     */
    public void setGold(int gold) {
        this.gold.set(gold);
    }

    /**
     *
     * @param decrease
     */
    public void decreaseGold(int decrease) {
        setGold(gold.get() - decrease);
    }

    /**
     *
     * @param increase
     */
    public void increaseGold(int increase) {
//        System.out.println("increase: " + increase);
        setGold(gold.get() + increase);
    }

}
