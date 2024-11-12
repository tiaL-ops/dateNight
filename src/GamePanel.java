import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen setting
    final int originalSpriteSize= 16;
    final int scale=3;
    final int spriteSize= originalSpriteSize * scale;


    final int maxScreenCol=16;
    final int maxScreenRow=12 ;

    final int screenWidth= spriteSize * maxScreenRow;
    final int screenHeight=spriteSize  * maxScreenCol;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; 


    //player position
    int xPosition= 100;
    int yPosition= 100;
    int playerSpeed=4;

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);// can be focused

    }

    public void startGameThread(){
        gameThread = new Thread(this); //Passing game panel into the Thread
        gameThread.start();
    }


    @Override
    public void run() {
        while(gameThread != null){
             System.out.println("Looping!");

             long currentTime= System.nanoTime();
             

             //update char postion
             update();
             repaint();
             //draw the screen
        }
       
    }

    public void update(){
        if(keyH.upPressed == true){
            yPosition-= playerSpeed;
        }else if(keyH.downPressed == true){
            yPosition+= playerSpeed;
        }else if(keyH.leftPressed == true){
            xPosition+= playerSpeed;
        }else if(keyH.rightPressed== true){
            xPosition-= playerSpeed;
        }

    }
    public void paintComponent(Graphics graph){
        super.paintComponent(graph);

        Graphics2D g2 =(Graphics2D) graph;
        g2.setColor(Color.CYAN);
        g2.fillRect(xPosition, yPosition, spriteSize, spriteSize);
        g2.dispose();
    } 
 

}
