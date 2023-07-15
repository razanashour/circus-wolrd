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
public class observingscore extends Observer {

    public observingscore(CircusWorld c) {
        super(c);
    }

    @Override
    public void update() {
        c.setScore(c.getScore()+1);
      
    }

    
}
