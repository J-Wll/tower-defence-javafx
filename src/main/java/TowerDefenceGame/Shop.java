/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author user
 */
public class Shop {

    private GameWindow gameWindow;
    private GameStatePublisher gameManager;
    private Textures textures;
    private Tower spawning;
    private Director director = new Director();

    /**
     *
     * @param gameWindow
     */
    public Shop(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.gameManager = GameStatePublisher.getInstance();
        this.textures = gameWindow.getTextures();
    }

    /**
     *
     * @param renderTo
     */
    public void render(Group renderTo) {
//        could make this render from an array of buttons but it would take longer to write and there's only going to be 10 buttons absolute max
        int buttonYStart = 710;
        int buttonXStart = 25;
        int spacing = 140;
        Label towersLb = new Label("Towers");
        towersLb.setLayoutY(buttonYStart - 30);
        towersLb.setLayoutX(buttonXStart);
        renderTo.getChildren().add(towersLb);

        ShopButton tbutton = new ShopButton("Laser Tower\nCost 25 gold", buttonXStart, buttonYStart, 25, Values.laserTower);
        renderTo.getChildren().add(tbutton);

        ShopButton tbutton1 = new ShopButton("Flame Tower\nCost 50 gold", buttonXStart + spacing, buttonYStart, 50, Values.flameTower);
        renderTo.getChildren().add(tbutton1);

        ShopButton tbutton2 = new ShopButton("Shotgun Laser Tower\nCost 50 gold", buttonXStart + spacing * 2, buttonYStart, 50, Values.shotgunLaserTower);
        renderTo.getChildren().add(tbutton2);

        ShopButton tbutton3 = new ShopButton("Behemoth Tower\nCost 100 gold", buttonXStart + spacing * 3, buttonYStart, 100, Values.behemothTower);
        renderTo.getChildren().add(tbutton3);

        Label powersLb = new Label("Power-ups");
        powersLb.setLayoutY(buttonYStart - 30);
        powersLb.setLayoutX(buttonXStart + spacing * 4 + 50);
        renderTo.getChildren().add(powersLb);

        ShopButton lbutton = new ShopButton("0.25x current enemies speed\nCost 100 gold", buttonXStart + spacing * 4 + 50, buttonYStart, 100, "slow-power", true);
        renderTo.getChildren().add(lbutton);

        ShopButton lbutton1 = new ShopButton("1.5x tower damage for 10s\nCost 100 gold", buttonXStart + spacing * 5 + 50, buttonYStart, 100, "damage-power", true);
        renderTo.getChildren().add(lbutton1);

        ShopButton lbutton2 = new ShopButton("Buy 40 HP\nCost 100 gold", buttonXStart + spacing * 6 + 50, buttonYStart, 100, "hp-power", true);
        renderTo.getChildren().add(lbutton2);

    }

    /**
     *
     * @param towerVal
     * @return
     */
    public Tower createTower(int towerVal) {
        TowerBuilder builder = new TowerBuilder();
        switch (towerVal) {
            case Values.laserTower:
                director.constructTowerLaser(builder);
                break;
            case Values.shotgunLaserTower:
                director.constructTowerShotgun(builder);
                break;
            case Values.behemothTower:
                director.constructTowerBehemoth(builder);
                break;
            case Values.flameTower:
                director.constructTowerFlame(builder);
                break;
            default:
                System.err.println("towername switch default triggered");
                director.constructTowerLaser(builder);
                break;
        }
        return builder.getTower();
    }

    private class ShopButton extends Button {

//        tower buttons
        public ShopButton(String content, int x, int y, int price, int towerVal) {
            // regular button init
            super(content);
            setLayoutX(x);
            setLayoutY(y);
            setOnAction((ActionEvent e) -> {
                if (gameWindow.getMouseItemActive()) {
                    gameWindow.setMouseItem(false, null);
                } else if (gameManager.getGold() >= price) {
                    Tower tower = createTower(towerVal);
                    spawning = tower;
                    gameWindow.setMouseItem(true, tower);
                }
            }
            );
        }

//        powerup buttons
        public ShopButton(String content, int x, int y, int price, String event, Boolean power) {
            super(content);
            setLayoutX(x);
            setLayoutY(y);
            setOnAction((ActionEvent e) -> {
                if (gameManager.getGold() >= price) {
                    gameManager.decreaseGold(price);
                    if ("hp-power".equals(event)) {
                        gameManager.increaseHp(40);
                    } else {
                        gameManager.notify(event);
                    }
                }
            });
        }
    }
}
