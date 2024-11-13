import javax.swing.JFrame;
import javax.swing.JLabel;


public class DateMain{

    public static void main(String args[]){
        JFrame window = new JFrame("DateNight");
        JLabel label = new JLabel("Source");
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(label);
        
        window.setResizable(false);

        GamePanel gamePanel= new GamePanel();
        window.add(gamePanel);
        gamePanel.startGameThread();

        window.pack();

        window.setLocationRelativeTo(null);
        
        window.setVisible(true);
    }


}