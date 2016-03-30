/**
 * Description	:Engine for the brick game
 * Copyright	:Copyright (c) 2014
 * Company		:Embla Software Innovations (Pvt) Ltd
 * Created on	:2014.09.01
 * @author 		:Chandimal
 * @version 	:1.0
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends Canvas implements KeyListener{

	private static final long serialVersionUID = 1L;

	BufferedImage buffer; // Create the buffer
        Ball ball;
        Bat bat;
        Brick[][] brick;
        private int gameHeight;
        boolean isLeft;
        boolean isRight;
        BufferedImage img_background;
        BufferedImage img_bat;
        BufferedImage img_brick;
        int powerI;
        int powerJ;
        int power_count;
        Missile missile;
        boolean take_power;boolean shoot; 
        boolean power;
        private int total;
        PowerBall powerball;
        int gmeWidth;
        int gmeHeight;
	/**
	 * Create the game using the width and the height specified
	 */
	public Game(Dimension dim) {
                gmeHeight = dim.height; gmeWidth = dim.width;
		buffer = new BufferedImage(dim.width, dim.height,
				BufferedImage.TYPE_INT_RGB);
		this.setIgnoreRepaint(true); // Ignore repainting as we are doing all
                total = 0;										
                                             // the drawing stuff
                ball = new Ball(dim.width,dim.height,0,200,15);
                bat = new Bat(dim.width,dim.height,((dim.width)/2) - 30,dim.height - 20,20,60,1);
                brick = new Brick[5][7];
                power = false; take_power = false; power_count =0;shoot =false;
                for (int j=0;j<5;j++){
                    for(int i=0; i<7; i++){
                        brick[j][i] = new Brick(dim.width,dim.height,(0+(67*i)),(0+(25*j)),20,60,15);
                    }
                }
                Random r = new Random();
                powerJ = r.nextInt(5);
                powerI = r.nextInt(7);
                powerball = new PowerBall(dim.width,dim.height,brick[powerJ][powerI].getX(),brick[powerJ][powerI].getY(),15);
                this.gameHeight = dim.height;
                isLeft = false;
                isRight = false;
                try {
                    img_background = ImageIO.read(new File("Image2.jpg"));
                    img_bat = ImageIO.read(new File("Image3.jpg"));
                    img_brick = ImageIO.read(new File("Image4.jpg"));
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

	/**
	 * Start the game
	 */
	public void Start() {

		while (true) {
                        if(isLeft) bat.setLeft();
                        if(isRight) bat.setRight();
                        brickCollision();
                        detectCollision();
			// Draw the buffer
			drawBuffer();
			// Paint the buffer on screen
			drawScreen();

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
        public void detectCollision(){
            Rectangle rectBall = new Rectangle(ball.getX(),ball.getY(),ball.getSize(),ball.getSize());
            Rectangle rectBat = new Rectangle(bat.getX(),bat.getY(),bat.getObjectWidth(),bat.getObjectHeight());
            
            if (rectBall.intersects(rectBat)){
                if(isLeft){
                    if (ball.get_dx()>0){
                        ball.set_dx(-(ball.get_dx()));
                        ball.reverse();
                    }else{
                        ball.reverse();
                    }
                }else if(isRight){
                    if (ball.get_dx()>0){
                        ball.reverse();
                    }else{
                        ball.set_dx(-(ball.get_dx()));
                        ball.reverse();
                    }
                }else{
                    ball.reverse();
                }
            }
            
            Rectangle rectBat1 = new Rectangle(bat.getX(),bat.getY(),bat.getObjectWidth(),bat.getObjectHeight());
            Rectangle rectPowerBall1 = new Rectangle(powerball.getX(),powerball.getY(),powerball.getSize(),powerball.getSize());
            
            if (rectBat1.intersects(rectPowerBall1)){
                JOptionPane.showMessageDialog(this, "You have recieved the super power! \nUse them well !!", "Brick Game", JOptionPane.OK_OPTION);
                powerball.setX(1000);
                take_power = true;
            }
            if(shoot){
                Rectangle missile1 = new Rectangle(missile.getX(),missile.getY(),missile.getSize(),missile.getSize());
                for(int j=0;j<5;j++){
                    for(int i=0;i<7;i++){
                        Rectangle rectBrick11 = new Rectangle(brick[j][i].getX(),brick[j][i].getY(),brick[j][i].getObjectWidth(),brick[j][i].getObjectHeight());
                        if (missile1.intersects(rectBrick11) ){
                            if (brick[j][i].getX()<1000){
                                brick[j][i].setX(1000);
                                total += 10;
                                missile.setX(bat.getX()+25);
                                missile.setY(bat.getY());
                                power_count++;
                                if (power_count ==5){take_power=false;}
                                System.out.println("Total :- " + total);
                            }
                        }
                    }
                }
            }
            
            if (ball.getY()> gameHeight-ball.getSize()){
                System.out.println("Game Over!, your total points :- " + total);
                JOptionPane.showMessageDialog(this, "Game over ! \nYour total :- " + total + "\nUsed super power :- " + power_count, "Brick Game Scoreboard", JOptionPane.OK_OPTION);
                total = 0;
                System.exit(0);
                ball.setX(0);
                ball.setY(200);
            }
        }
        
        public void brickCollision(){
            Rectangle rectBall = new Rectangle(ball.getX(),ball.getY(),ball.getSize(),ball.getSize());
            for(int j=0;j<5;j++){
                for(int i=0;i<7;i++){
                    Rectangle rectBrick11 = new Rectangle(brick[j][i].getX(),brick[j][i].getY(),brick[j][i].getObjectWidth(),brick[j][i].getObjectHeight());
                    if (rectBall.intersects(rectBrick11) ){
                        if (brick[j][i].getX()<1000){
                            ball.reverse();
                            brick[j][i].setX(1000);
                            total += 10;
                            System.out.println("Total :- " + total);
                            if (i==powerI){
                                if(j==powerJ){
                                    power = true;
                                }
                            }
                        }
                    }
                }
            }
        }
	/**
	 * Draw the image buffer
	 */
	public void drawBuffer() {
                
		Graphics2D b = buffer.createGraphics();
		// Random color background
		//Color c = new Color(new Random().nextInt());
		//b.setColor(Color.BLACK);
                b.drawImage(img_background, null, 0, 0);
		//b.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
                b.setColor(Color.WHITE);
                b.fillOval(ball.getX(), ball.getY(), ball.getSize(), ball.getSize());
                
                if (power){
                    b.setColor(Color.yellow);
                    b.fillOval(((powerball.getX()+60)/2), powerball.getY(), powerball.getSize(), powerball.getSize());
                }
                if(take_power){
                    if(!shoot){
                        shoot = true;
                        missile = new Missile(gmeWidth,gmeHeight,bat.getX()+25,bat.getY(),15);
                    }
                }
                
                if(take_power){
                    if(shoot){
                            b.setColor(Color.red);
                            b.fillOval((missile.getX()), missile.getY(), missile.getSize(), missile.getSize());
                    }
                }
                
                for (int j=0;j<5;j++){
                    for (int i=0;i<7;i++){
                        drawBricks(b,brick[j][i].getX(),brick[j][i].getY(),brick[j][i].objectwidth, brick[j][i].objectHeight);
                    }
                }
                b.fillRoundRect(bat.getX(), bat.getY(), bat.getObjectWidth(), bat.getObjectHeight(), 10, 10);
                b.drawImage(img_bat, null, bat.getX(), bat.getY());  
	}
        
        public void drawBricks(Graphics2D b,int startX, int startY, int objWidth, int objHeight){
            b.fillRect(startX, startY, objWidth, objHeight);
            b.drawImage(img_brick, null, startX, startY);
        }
	/**
	 *  Update it to the screen
	 */
	public void drawScreen() {
		Graphics2D g = (Graphics2D) this.getGraphics();
                ball.update();
                bat.update();
                if(take_power){missile.update();}
                if(power){powerball.update();}
		g.drawImage(buffer, 0, 0, this);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 37){
            isLeft = true; 
        }
        if (e.getKeyCode() == 39){
            isRight = true; 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 37){
            isLeft = false; 
        }
        if (e.getKeyCode() == 39){
            isRight = false; 
        }
    }

}
