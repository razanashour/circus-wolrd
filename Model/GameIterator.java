package Model;


import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.Iterator;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class GameIterator implements Iterator<GameObject>{
       int index = 0;
    List<GameObject> I;

    public GameIterator(List<GameObject> I) {
        this.I = I;
    }

    @Override
    public boolean hasNext() {
        if (index < I.size()) {
            return true;
        } else {
            return false;
        }   
    }

    @Override
    public GameObject next() {
     GameObject m = I.get(index);
         index=index+1;     
        return m;
       
    }
}
