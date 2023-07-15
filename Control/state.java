package Control;


import eg.edu.alexu.csd.oop.game.GameObject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public abstract class state {
     protected GameObject g;

    public state(GameObject g) {
        this.g = g;
    }

    public abstract void move(int x, int y); 
}
