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
    private IntegerProperty intensity;
    private IntegerProperty remaining;
    private int currentLevel;

    private GameStatePublisher() {
    }

    public static GameStatePublisher getInstance() {
        if (instance == null) {
            instance = new GameStatePublisher();
        }
        return instance;
    }

    public void init(int hp, int gold, int currentLevel) {
        this.hp = new SimpleIntegerProperty(hp);
        this.gold = new SimpleIntegerProperty(gold);
        this.intensity = new SimpleIntegerProperty(0);
        this.remaining = new SimpleIntegerProperty(0);
        this.subscribers = new ArrayList();
        this.currentLevel = currentLevel;
    }

    /**
     *
     */
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

        StringBinding goldTextBinding = Bindings.createStringBinding(
                () -> "Gold: " + gold.get(),
                gold
        );
        Label goldLb = new AutoLabel(goldTextBinding, "gold-lb", 25, rY);
        renderTo.getChildren().add(goldLb);

        Label levelLb = new Label("Current level: " + this.currentLevel);
        levelLb.setLayoutX(200);
        levelLb.setLayoutY(rY);
        renderTo.getChildren().add(levelLb);

        StringBinding remainingBinding = Bindings.createStringBinding(
                () -> "Enemies remaining: " + remaining.get(),
                remaining
        );
        Label remainingLb = new AutoLabel(remainingBinding, "rm-lb", 450, rY);
        renderTo.getChildren().add(remainingLb);

        StringBinding intensityBinding = Bindings.createStringBinding(
                () -> "Intensity factor: " + intensity.get(),
                intensity
        );
        Label intensityLb = new AutoLabel(intensityBinding, "in-lb", 775, rY);
        renderTo.getChildren().add(intensityLb);

        StringBinding hpTextBinding = Bindings.createStringBinding(
                () -> "HP: " + hp.get(),
                hp
        );
        Label hpLb = new AutoLabel(hpTextBinding, "hp-lb", 1060, rY);
        renderTo.getChildren().add(hpLb);

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

        if (this.hp.get() <= 0) {
            GameWindow.getInstance().newLevel(1, "YOU LOSE\nGAME OVER\nThanks for playing\nRestarting at level 1.....");
        }
    }

    /**
     *
     * @param intensity
     */
    public void setIntensity(int intensity) {
        this.intensity.set(intensity);
    }

    /**
     *
     * @param remaining
     */
    public void setRemaining(int remaining) {
        this.remaining.set(remaining);
    }

    /**
     *
     * @param by
     */
    public void decreaseRemaining(int by) {
        this.remaining.set(remaining.get() - by);

        if (remaining.get() <= 0) {
            GameWindow.getInstance().newLevel(currentLevel + 1, "Level beat\nLoading the next...");
        }
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
        setGold(gold.get() + increase);
    }

//    Auto updates with binding
    private class AutoLabel extends Label {

        public AutoLabel(StringBinding strBi, String styleClass, int x, int y) {
            super();
            setLayoutX(x);
            setLayoutY(y);
            getStyleClass().add(styleClass);
            textProperty().bind(strBi);
        }
    }
}
