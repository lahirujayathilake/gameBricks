
public class PowerBall extends GameEntry{

    private int dy;
    
    public PowerBall(int gameWidth, int gameHeight, int startX, int startY, int size) {
        super(gameWidth,gameHeight, startX, startY, size, size, 0);
        dy=3;
    }
    
    public void update(){
        setY((y+dy));
    }    
    
    public int getSize(){
        return objectHeight;
    }
}
