/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

import javafx.scene.Group;
import javafx.scene.control.Button;

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
        int spacing = 100;
        Button button = new Button("Click Me");
        button.setLayoutY(680);
        button.setLayoutX(25);
        renderTo.getChildren().add(button);

        Button button1 = new Button("Click Me");
        button1.setLayoutY(680);
        button1.setLayoutX(25 + spacing);
        renderTo.getChildren().add(button1);

        Button button2 = new Button("Click Me");
        button2.setLayoutY(680);
        button2.setLayoutX(25 + spacing * 2);
        renderTo.getChildren().add(button2);

        Button button3 = new Button("Click Me");
        button3.setLayoutY(680);
        button3.setLayoutX(25 + spacing * 3);
        renderTo.getChildren().add(button3);
    }

}
