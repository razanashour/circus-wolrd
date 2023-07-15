package Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MSI
 */
public class Level3_Strategy implements IStrategy {
    
         @Override
    public int getSpeed() {
        return 3;
    }

    @Override
    public int getTimeout() {
        //return 1;
         return 1 * 60 * 1000;
    }

    @Override
    public int getmaxnumofplates() {
        return 15;
    }
   @Override
    public int getnumberofbomb() {
        return 6;
    } 
}
