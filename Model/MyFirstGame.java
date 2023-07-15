/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Model;

import Control.CircusWorld;


/**
 *
 * @author michael.said
 */
public class MyFirstGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Level1_Strategy e = new Level1_Strategy();
        GameController gameController = new GameController(() -> new CircusWorld(e,800, 500));
        gameController.start();
    }

}
