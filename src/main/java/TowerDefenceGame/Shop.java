/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private final Textures textures;
    private int spawning;

    public Shop(GameWindow gameWindow, GameStatePublisher gameManager) {
        this.gameWindow = gameWindow;
        this.gameManager = gameManager;
        this.textures = gameWindow.getTextures();
    }

    public void towerRefund() {
        switch (spawning) {
            case Values.laserTower:
                gameManager.increaseGold(25);
                break;
            case Values.flameTower:
                gameManager.increaseGold(50);
                break;
            case Values.behemothTower:
                gameManager.increaseGold(100);
                break;
            case Values.shotgunLaserTower:
                gameManager.increaseGold(100);
                break;
        }
    }

    public void render(Group renderTo) {
//        could make this render from an array of buttons but it would take longer to write and there's only going to be 10 buttons absolute max
        int buttonYStart = 710;
        int buttonXStart = 25;
        int spacing = 140;
        Label towersLb = new Label("Towers");
        towersLb.setLayoutY(buttonYStart - 30);
        towersLb.setLayoutX(buttonXStart);
        renderTo.getChildren().add(towersLb);

        var tbutton = new ShopButton("Laser Tower\nCost 25 gold", buttonXStart, buttonYStart, Values.laserTower, 25);
        renderTo.getChildren().add(tbutton);

        var tbutton1 = new ShopButton("Flame Tower\nCost 50 gold", buttonXStart + spacing, buttonYStart, Values.flameTower, 50);
        renderTo.getChildren().add(tbutton1);

        var tbutton2 = new ShopButton("Behemoth Tower\nCost 100 gold", buttonXStart + spacing * 2, buttonYStart, Values.behemothTower, 100);
        renderTo.getChildren().add(tbutton2);

        var tbutton3 = new ShopButton("Shotgun Laser Tower\nCost 100 gold", buttonXStart + spacing * 3, buttonYStart, Values.shotgunLaserTower, 100);
        renderTo.getChildren().add(tbutton3);

        Label powersLb = new Label("Power-ups");
        powersLb.setLayoutY(buttonYStart - 30);
        powersLb.setLayoutX(buttonXStart + spacing * 4 + 50);
        renderTo.getChildren().add(powersLb);

        var lbutton = new ShopButton("1/2 current enemies speed\nCost 100 gold", buttonXStart + spacing * 4 + 50, buttonYStart, 100, "slow-power");
        renderTo.getChildren().add(lbutton);

        var lbutton1 = new ShopButton("1.5x tower damage for 10s\nCost 100 gold", buttonXStart + spacing * 5 + 50, buttonYStart, 100, "damage-power");
        renderTo.getChildren().add(lbutton1);

        var lbutton2 = new ShopButton("Buy 40 HP\nCost 100 gold", buttonXStart + spacing * 6 + 50, buttonYStart, 100, "hp-power");
        renderTo.getChildren().add(lbutton2);

    }

    private class ShopButton extends Button {

//        tower buttons
        public ShopButton(String content, int x, int y, int towerVal, int towerPrice) {
            super(content);
            setLayoutX(x);
            setLayoutY(y);
            setOnAction((ActionEvent e) -> {
                if (gameWindow.getMouseItemActive()) {
                    towerRefund();
                    gameWindow.setMouseItem(false, null);
                } else if (gameManager.getGold() >= towerPrice) {
                    spawning = towerVal;
                    gameManager.decreaseGold(towerPrice);
                    gameWindow.setMouseItem(true, textures.getText().get(towerVal));
                }
            }
            );
        }

//        powerup buttons
        public ShopButton(String content, int x, int y, int price, String event) {
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
