import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;

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
    Player player = new Player(this,keyH); 
    Obstacle obstacle = new Obstacle(this, keyH);

    int FPS =60;

    //player position
    int xPosition= 100;
    int yPosition= 100;
    int playerSpeed=4;



    // Ball properties
    int ballX, ballY;            
    int ballSpeedX, ballSpeedY;   
    int ballSpeed = 3;           

    Random random = new Random();
    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);// can be focused
        randomizeBall();

    }

    public void startGameThread(){
        gameThread = new Thread(this); //Passing game panel into the Thread
        gameThread.start();
    }


    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; //one second / 60 -- > 0.01 
        double nextDrawTime= System.nanoTime() + drawInterval ;


        while(gameThread != null){
             //System.out.println("Looping!");

             long currentTime= System.nanoTime();
            
             update();
             repaint();
             

             try {
                double remainingTime= nextDrawTime - System.nanoTime();
                remainingTime= remainingTime/ 1000000 ;

                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            nextDrawTime+= drawInterval;
        }
       
    }

    public void update(){
        player.update();
        obstacle.update();
        updateBallPosition();

    }
    public void paintComponent(Graphics graph){
        super.paintComponent(graph);

        Graphics2D g2 =(Graphics2D) graph;
       player.draw(g2);
       obstacle.draw(g2);
       g2.setColor(Color.RED); 
    g2.fillOval(ballX, ballY, spriteSize, spriteSize); 

    g2.dispose();
        g2.dispose();
    } 
    
    public void randomizeBall() {
        ballX = random.nextInt(screenWidth - spriteSize);
        ballY = random.nextInt(screenHeight - spriteSize);

        // random initail direction
        ballSpeedX = (random.nextBoolean() ? 1 : -1) * ballSpeed;
        ballSpeedY = (random.nextBoolean() ? 1 : -1) * ballSpeed;
    }

    public void updateBallPosition() {

        ballX += ballSpeedX;
        ballY += ballSpeedY;

        
        if (ballX <= 0 || ballX >= screenWidth - spriteSize) {
            ballSpeedX = -ballSpeedX;
        }
        if (ballY <= 0 || ballY >= screenHeight - spriteSize) {
            ballSpeedY = -ballSpeedY;
        }

        
        if (checkCollisionWithPlayer()) {
            ballSpeedX = -ballSpeedX;
            ballSpeedY = -ballSpeedY;
        }
    }

    public boolean checkCollisionWithPlayer() {
        int playerRight = player.x + spriteSize;
        int playerBottom = player.y + spriteSize;
        int ballRight = ballX + spriteSize;
        int ballBottom = ballY + spriteSize;

        return ballX < playerRight &&
               ballRight > player.x &&
               ballY < playerBottom &&
               ballBottom > player.y;
    }

}
