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

    public Shop(GameWindow gameWindow, GameStatePublisher gameManager) {
        this.gameWindow = gameWindow;
        this.gameManager = gameManager;
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

        Button button = new Button("Laser Tower\nCost 25 gold");
        button.setLayoutY(buttonYStart);
        button.setLayoutX(buttonXStart);
        renderTo.getChildren().add(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Laser");
                gameManager.decreaseGold(25);
            }
        });

        Button button1 = new Button("Flame Tower\nCost 50 gold");
        button1.setLayoutY(buttonYStart);
        button1.setLayoutX(buttonXStart + spacing);
        renderTo.getChildren().add(button1);

        Button button2 = new Button("Behemoth Tower\nCost 100 gold");
        button2.setLayoutY(buttonYStart);
        button2.setLayoutX(buttonXStart + spacing * 2);
        renderTo.getChildren().add(button2);

        Button button3 = new Button("Shotgun Laser Tower\nCost 100 gold");
        button3.setLayoutY(buttonYStart);
        button3.setLayoutX(buttonXStart + spacing * 3);
        renderTo.getChildren().add(button3);

        Label powersLb = new Label("Power-ups");
        powersLb.setLayoutY(buttonYStart - 30);
        powersLb.setLayoutX(buttonXStart + spacing * 4 + 50);
        renderTo.getChildren().add(powersLb);

    }

}
