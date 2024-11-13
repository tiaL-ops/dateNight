import java.awt.Color;
import java.awt.Graphics2D;


public class Obstacle extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Obstacle(GamePanel gp, KeyHandler keyH){
       this.gp=gp;
       this.keyH= keyH;
       setDefaultValues();
    }

   

    public void setDefaultValues(){
       x=100;
       y=100;
       speed=4 ;
    }

    public void update(){
       x-=speed;
       if (x < 10){
            x=gp.screenWidth;
    }


    }

    public void draw(Graphics2D g2){
       g2.setColor(Color.white);
       g2.fillOval(x, y, gp.spriteSize,gp.spriteSize);
       
}
}
