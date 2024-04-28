/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author user
 */
public interface Tower {

    void attack(Monster[] monsters);

    int getValue();

    void update(String event);

    int getAttackRadius();

    void setPos(int x, int y);
}
