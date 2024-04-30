/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TowerDefenceGame;


/**
 *
 * @author user
 */
public interface Tower {

    /**
     *
     * @param monsters
     */
    void attack(Monster[] monsters);

    /**
     *
     * @return
     */
    int getValue();

    /**
     *
     * @param event
     */
    void update(String event);

    /**
     *
     * @return
     */
    int getAttackRadius();

    /**
     *
     * @param x
     * @param y
     */
    void setPos(int x, int y);
}
