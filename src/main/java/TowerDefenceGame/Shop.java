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
    private int spawning;
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
     */
    public void towerRefund() {
        switch (spawning) {
            case Values.laserTower:
                gameManager.increaseGold(25);
                break;
            case Values.flameTower:
                gameManager.increaseGold(50);
                break;
            case Values.shotgunLaserTower:
                gameManager.increaseGold(50);
                break;
            case Values.behemothTower:
                gameManager.increaseGold(100);
                break;

        }
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

        var tbutton = new ShopButton("Laser Tower\nCost 25 gold", buttonXStart, buttonYStart, 25, "TowerLaser");
        renderTo.getChildren().add(tbutton);

        var tbutton1 = new ShopButton("Flame Tower\nCost 50 gold", buttonXStart + spacing, buttonYStart, 50, "TowerFlame");
        renderTo.getChildren().add(tbutton1);

        var tbutton2 = new ShopButton("Shotgun Laser Tower\nCost 50 gold", buttonXStart + spacing * 2, buttonYStart, 50, "TowerShotgun");
        renderTo.getChildren().add(tbutton2);

        var tbutton3 = new ShopButton("Behemoth Tower\nCost 100 gold", buttonXStart + spacing * 3, buttonYStart, 100, "TowerBehemoth");
        renderTo.getChildren().add(tbutton3);

        Label powersLb = new Label("Power-ups");
        powersLb.setLayoutY(buttonYStart - 30);
        powersLb.setLayoutX(buttonXStart + spacing * 4 + 50);
        renderTo.getChildren().add(powersLb);

        var lbutton = new ShopButton("0.25x current enemies speed\nCost 100 gold", buttonXStart + spacing * 4 + 50, buttonYStart, 100, "slow-power", true);
        renderTo.getChildren().add(lbutton);

        var lbutton1 = new ShopButton("1.5x tower damage for 10s\nCost 100 gold", buttonXStart + spacing * 5 + 50, buttonYStart, 100, "damage-power", true);
        renderTo.getChildren().add(lbutton1);

        var lbutton2 = new ShopButton("Buy 40 HP\nCost 100 gold", buttonXStart + spacing * 6 + 50, buttonYStart, 100, "hp-power", true);
        renderTo.getChildren().add(lbutton2);

    }

    private class ShopButton extends Button {

//        tower buttons
        public ShopButton(String content, int x, int y, int price, String towerName) {
            // regular button init
            super(content);
            setLayoutX(x);
            setLayoutY(y);
            setOnAction((ActionEvent e) -> {
                if (gameWindow.getMouseItemActive()) {
                    towerRefund();
                    gameWindow.setMouseItem(false, null);
                } else if (gameManager.getGold() >= price) {
                    Tower tower;
                    TowerBuilder builder = new TowerBuilder();
                    switch (towerName) {
                        case "TowerLaser":
                            director.constructTowerLaser(builder);
                            tower = builder.getTower();
                            break;
                        case "TowerShotgun":
                            director.constructTowerShotgun(builder);
                            tower = builder.getTower();
                            break;
                        case "TowerBehemoth":
                            director.constructTowerBehemoth(builder);
                            tower = builder.getTower();
                            break;
                        case "TowerFlame":
                            director.constructTowerFlame(builder);
                            tower = builder.getTower();
                            break;
                        default:
                            System.err.println("towername switch default triggered");
                            director.constructTowerLaser(builder);
                            tower = builder.getTower();
                            break;
                    }
                    spawning = tower.getValue();
                    gameManager.decreaseGold(price);
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
