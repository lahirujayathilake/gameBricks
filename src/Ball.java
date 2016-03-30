
public class Ball extends GameEntry {
    
    private int dx;
    private int dy;
    
    public Ball(int gameWidth, int gameHeight, int startX, int startY, int size){
        super(gameWidth,gameHeight,startX,startY,size,size,0);
        dx = 1;
        dy = 1;
    }
    
    public void reverse(){
        dy = - dy;
    }
    
    public int get_dx(){return dx;}
    public int get_dy(){return dy;}
    public void set_dx(int dx){this.dx = dx;}
    public void set_dy(int dy){this.dy = dy;}
    
    public void update(){
        if (x + objectHeight>gameWidth){
            dx = -dx;
        }
        if (x <0){
            dx = -dx;
        }
        if (y +objectHeight>gameHeight){
            dy = -dy;
        }
        if (y <0){
            dy = -dy;
        }
        
        setX(x + dx);
        setY(y + dy);
    }    
    
    public int getSize(){
        return objectHeight;
    }
}
