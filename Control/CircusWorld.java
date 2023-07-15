package Control;


import Model.BarObject1;//s
import Model.GameIterator;
import Model.IStrategy;
import Model.ImageObject;
import Model.Shape;
import Model.ShapeFactory;
import java.util.LinkedList;
import java.util.List;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.awt.Color;

public class CircusWorld implements World {

   // private static final int MAX_TIME = 1 * 60 * 1000;	// 1 minute
    private int score = 0;
    private  long startTime = System.currentTimeMillis();//was final
    private final int width ;
    private final int height;
    private final List<GameObject> constant = new LinkedList<GameObject>();
    private final List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();
    private List<GameObject> left = new LinkedList<GameObject>();
    private List<GameObject> right = new LinkedList<GameObject>();
     //private final ShapeFactory ShapeFactory =new ShapeFactory();
    private IStrategy strategy;
    private int j = 0;
    private int k = 0;

   
    public int getScore() {
        return score;
    }// for observer

    public void setScore(int score) {
        this.score = score;
    }
   
    private ShapeFactory F;

    // private int setLeftOrRight;
    public CircusWorld(IStrategy x, int screenWidth, int screenHeight) {
        F = ShapeFactory.getInstance();//use singelton
         strategy = x;
        width = screenWidth;
        height = screenHeight;
        ImageObject bc =new ImageObject(width/5000,height/5000,"/bc2.png");
        constant.add(bc);
        F.getInstance();
     
//        control.add(new ImageObject(screenWidth / 3, (int) (screenHeight * 0.6), "/Clown3.png"));
        control.add(F.getshape(screenWidth / 3, (int) (screenHeight * 0.6), "newcolwn1"));
        constant.add(new BarObject1(0, 50, 250, true, Color.BLACK));
        constant.add(new BarObject1(width - 250, 50, 250, true, Color.BLACK));
        for (int i = 0; i < strategy.getmaxnumofplates(); i++) {

            Shape p= (Shape) F.getshape( -200* i, 41, "plate"); 
            
                moving.add(p);
               
             j=i+1;
        }
        for (int i = 0; i <  strategy.getnumberofbomb(); i++) {
       
            Shape p1= (Shape) F.getshape(-200 * j, 15, "bomb"); 
                 moving.add(p1);
                 j++;
        }
    
        for (int i = 0; i <  strategy.getmaxnumofplates(); i++) {
//  PlateObject p = new PlateObject(width + 70* i, 43, "/plate" + ((int) (Math.random() * 1000) % 2 + 1) + ".png");
            Shape p = (Shape) F.getshape(width + 70 *(4*i) , 41, "plate");
             //Shape p1 = (Shape) F.getshape(width + 70 *(4*i) , 15, "star"); 
           
           p.setLOrRBar(1);
           moving.add(p);                 
           k=i+1;

          // p1.setLeftOrRightBar(1);
          // moving.add(p1);
        }
        
        for (int i = 0; i <  strategy.getnumberofbomb(); i++) {
//    
            Shape p1= (Shape) F.getshape(width+90* (4* k), 15, "bomb"); 
          
            
            p1.setLOrRBar(1);
          
           moving.add(p1);
           k++;
           
        }
    }

    private boolean intersect(GameObject o1, GameObject o2) {
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }

    @Override
    public boolean refresh() {
        
        boolean timeout = System.currentTimeMillis() - startTime >  strategy.getTimeout(); // time end and game over
        GameObject clown = control.get(0);
//        for (int i = 0; i < moving.size(); i++) {
GameIterator iterator=new GameIterator(moving) ;
           while(iterator.hasNext()) {
            GameObject m = iterator.next();
            Shape p = (Shape) m;
            int x = m.getX();
            int y = m.getY();
            y = y + (int) (Math.random() * 2000) % 5;
         
                  if (p.getLOrRBar() == 0) {//left
                         x = x + strategy.getSpeed();
             
                 if (m.getX() + 150 > width/ 2 ) {
                    p.setCurrentState(new movingState(p));
                } //plate has fallen        
        }
                  else{//RIGHT
               x = x -  strategy.getSpeed();
                    
                  if (m.getX() < width - 290) {
                    p.setCurrentState(new movingState(p)); 
                }
           }
            p.getCurrentState().move(x, y);
            if (left.isEmpty()) {
                if (Intersectleft(m)) {
                    if(p.getPath().equals("/bomb.png"))
                    {
                        moving.remove(m);
                        moving.clear();
                        startTime =0;
                        return timeout;//GAMEOVER
                    }
                    else{
                    moving.remove(m);
                    p.setC((ImageObject) clown);
                    p.setY(clown.getY() - p.getHeight());
                    p.setHorizontal(true);
                    p.setType(1);
                    control.add(m);
                    left.add(m);
                    }
                }
            } else {
                if (intersect(m, left.get(left.size() - 1))) {
                    if(p.getPath().equals("/bomb.png"))
                    {
                        moving.remove(m);
                        moving.clear();
                        startTime =0;
                        return timeout;
                    } 
                    
                    else{
                    moving.remove(m);
                    p.setC((ImageObject) clown);
                    p.setY(left.get(left.size() - 1).getY() - p.getHeight());
                    p.setHorizontal(true);
                    p.setType(1);
                    control.add(m);
                    left.add(m);}
                }
            }
            if (right.isEmpty()) {
                if (Intersectright(m)) {
                     if(p.getPath().equals("/bomb.png"))
                    {
                        moving.remove(m);
                        moving.clear();
                        startTime =0;
                        return timeout;
                    }
                     else{
                    moving.remove(m);
                    p.setC((ImageObject) clown);
                    p.setY(clown.getY() - p.getHeight());
                    p.setHorizontal(true);
                    p.setType(2);
                    control.add(m);
                    right.add(m);
                     }
                }
            } else {
                if (intersect(m, right.get(right.size() - 1))) {
                     if(p.getPath().equals("/bomb.png"))
                    {
                        moving.remove(m);
                        moving.clear();
                        startTime =0;
                        return timeout;
                    }
                     else{
                    moving.remove(m);
                    p.setC((ImageObject) clown);
                    p.setY(right.get(right.size() - 1).getY() - p.getHeight());
                    p.setHorizontal(true);
                    p.setType(2);
                    control.add(m);
                    right.add(m);
                     }
                }
            }
            if (m.getY() > height) {
                 moving.remove(p);
                p.setCurrentState(new barState(p));
                p.setY(43);
                 if (p.getPath().equals("/bomb.png")) {
                        p.setY(15);
                    }
                if (p.getLOrRBar() == 0) {
                    p.setX(-150* j);
                    j++;
                } else {
                    p.setX(width + 200 * k);
                    k++;
                }
                moving.add(p);
                           }
            updateLeft();
            updateright();
        
            if (left.size() == 10) {
                return false;
            }
            if (right.size() == 10) {
                return false;
            }
        }
        return !timeout;   //if timeout=true, return false => Game Over
    }
    

     @Override
    public int getSpeed() {
        return 10;
    }

    @Override
    public int getControlSpeed() {
        return 20;
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return moving;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return control;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getStatus() {
        return "Score=" + score + "   |   Time=" + Math.max(0, ( strategy.getTimeout() - (System.currentTimeMillis() - startTime)) / 1000);	// update status
    }

    private boolean Intersectleft(GameObject o) {
        ImageObject clown = (ImageObject) control.get(0);
//        return (Math.abs(clown.getX() - o.getX()) <= o.getWidth() - 10
//                && o.getY() == control.get(0).getY() - 10);
        return (Math.abs(clown.getX() - o.getX()) <= 30&&clown.getY() - o.getY() >= 10 && clown.getY() - o.getY() <= 12);
    }

    private boolean Intersectright(GameObject o) {
        ImageObject clown = (ImageObject) control.get(0);
        return (Math.abs(clown.getX() + clown.getWidth() - (o.getWidth()*2)) <= o.getX() && (clown.getX() + clown.getWidth() >= o.getX())
                && (clown.getY() - o.getY() >= 10 && clown.getY() - o.getY() <= 12));
    }

    private void updateLeft() {
        if (left.size() >= 3) {
            Shape p1 = (Shape) left.get(left.size() - 1);
            Shape p2 = (Shape) left.get(left.size() - 2);
            Shape p3 = (Shape) left.get(left.size() - 3);
            if (p1.getPath().equals(p2.getPath()) && p2.getPath().equals(p3.getPath())) {
                left.remove(left.size() - 1);
                left.remove(left.size() - 1);
                left.remove(left.size() - 1);
                control.remove(p1);
                control.remove(p2);
                control.remove(p3);
                score++;
            }
        }
    }

    private void updateright() {
        if (right.size() >= 3) {
            Shape p1 = (Shape) right.get(right.size() - 1);
            Shape p2 = (Shape) right.get(right.size() - 2);
            Shape p3 = (Shape) right.get(right.size() - 3);
            if (p1.getPath().equals(p2.getPath()) && p2.getPath().equals(p3.getPath())) {
                right.remove(right.size() - 1);
                right.remove(right.size() - 1);
                right.remove(right.size() - 1);
                control.remove(p1);
                control.remove(p2);
                control.remove(p3);
                score++;
            }
        }
    }
}

