
public class Brick extends GameEntry{
    public Brick(int gameX, int gameY, int startX, int startY, int objHeight, int objWidth, int speed) {
        super(gameX, gameY, startX, startY, objHeight, objWidth, speed);
    }
    
    public void update(){
        this.setX(this.getX()+1);
    }
}

