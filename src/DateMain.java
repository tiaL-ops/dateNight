import javax.swing.JFrame;


public class DateMain{

    public static void main(String args[]){
        JFrame window = new JFrame("DateNight");
        //JLabel label = new JLabel("Hello Date");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        window.setResizable(false);

        GamePanel gamePanel= new GamePanel();
        window.add(gamePanel);
        gamePanel.startGameThread();

        window.pack();

        window.setLocationRelativeTo(null);
        
        window.setVisible(true);
    }


}