package Model;


import eg.edu.alexu.csd.oop.game.GameObject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class ShapeFactory {

    private static ShapeFactory S=null;
  

     private ShapeFactory() {
       
    }
    
    public GameObject getshape(int x,int y,String type)
    {
       if(type==null) {
           return null;
       }
       if (type.equals("plate"))
       {
           return new PlateObject(x,y,"/plate" +"_" +((int) (Math.random() * 1000)%3+1 ) + ".png");
       }
       else if(type.equals("newcolwn1"))
       {
          return new ImageObject(x,y,"/newcolwn1.png");
       }
       else if(type.equals("bomb"))
       {
          return new bombObject(x,y,"/bomb.png");
       }
      
       
       return null;
    }
    
    public static ShapeFactory getInstance(){
        
        if(S==null){
            S=new ShapeFactory();
        }
        return S;
    
    
    }  
    
    
    
    
    
}
