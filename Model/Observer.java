/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Control.CircusWorld;

/**
 *
 * @author HP
 */
public abstract class Observer {
   CircusWorld c;
    public Observer(CircusWorld c) {
        this.c = c;
    }
 public abstract void update();
    
}
