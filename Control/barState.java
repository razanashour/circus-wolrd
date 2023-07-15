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
public class barState extends state {
        public barState(GameObject g) {
        super(g);
    }

    @Override
    public void move( int x, int y) {
        g.setX(x);
    }
}
