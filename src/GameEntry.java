
public abstract class GameEntry {
    protected int x;
    protected int y;
    protected int objectHeight;
    protected int objectwidth;
    protected int speed;
    protected int gameHeight;
    protected int gameWidth;
    
    public GameEntry(int gameX, int gameY, int startX, int startY, int objHeight, int objWidth,int speed){
        this.x = startX;
        this.y = startY;
        this.gameHeight = gameY;
        this.gameWidth = gameX;
        this.objectHeight = objHeight;
        this.objectwidth = objWidth;
        this.speed = speed;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    public int getObjectHeight() {
        return objectHeight;
    }

    public int getObjectWidth() {
        return objectwidth;
    }

    public int getSpeed() {
        return speed;
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public int getGameHeight() {
        return gameHeight;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
