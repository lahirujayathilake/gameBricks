

public class Bat extends GameEntry {
    private int dx;
    
    public Bat(int gameWidth, int gameHeight,int startX, int startY, int batHeight, int batWidth, int speed){
        super(gameWidth,gameHeight,startX,startY,batHeight,batWidth,speed);
    }
    
    public void setRight(){
        this.dx = +(this.speed);
    }
    
    public void setLeft(){
        this.dx = -(this.speed);
    }
    
    public void update(){
       x = x + dx;
       if (x > (gameWidth-objectwidth)){
           this.x = gameWidth-objectwidth;
       }
       if (x < 0){
           this.x = 0;
       }
       dx = 0;
    }

    
    
}
