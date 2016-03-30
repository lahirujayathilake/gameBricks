
public class Missile extends GameEntry{

    
    private int dx;
    private int dy;
    
    public Missile(int gameWidth, int gameHeight, int startX, int startY, int size) {
        super(gameWidth,gameHeight, startX, startY, size, size, 0);
        dy=3;
    }
    
    public void update(){
        setY(y-(dy));
    }    
    
    public int getSize(){
        return objectHeight;
    }
}
