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

    private GameWindow game;
    private Group root;

    public Shop(Group root, GameWindow game) {
        this.game = game;
        this.root = root;
    }

    public void render() {
        int spacing = 80;
        Button button = new Button("Click Me");
        button.setLayoutY(680);
        button.setLayoutX(25);
        root.getChildren().add(button);

        Button button1 = new Button("Click Me");
        button1.setLayoutY(680);
        button1.setLayoutX(25 + spacing);
        root.getChildren().add(button1);

        Button button2 = new Button("Click Me");
        button2.setLayoutY(680);
        button2.setLayoutX(25 + spacing * 2);
        root.getChildren().add(button2);

        Button button3 = new Button("Click Me");
        button3.setLayoutY(680);
        button3.setLayoutX(25 + spacing * 3);
        root.getChildren().add(button3);
    }

}
