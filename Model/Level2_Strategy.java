package Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MSI
 */
public class Level2_Strategy  implements IStrategy{
        @Override
    public int getSpeed() {
        return 2;
    }

    @Override
    public int getTimeout() {
        //return 2;
         return 2 * 60 * 1000;
    }

    @Override
    public int getmaxnumofplates() {
        return 10;
    }
    @Override
    public int getnumberofbomb() {
        return 4;
    }
}
