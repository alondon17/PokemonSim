import javax.swing.*;
import java.awt.*;

public class FrameThing extends JFrame {
    public FrameThing(){
        Playingfield playingfield = new Playingfield();
        this.setBounds(0,0,900,900);
        this.setBounds(0, 0, 900, 900);
        this.setBackground(Color.GRAY);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(playingfield);
    }
    public class Playingfield extends JPanel {
        private ImageIcon playerIcon;
        private int[][] playinggrid = new int[10][10];
        private int[] playerX = new int[10];
        private int[] playerY = new int[10];

        public Playingfield() {

        }

        public void paint(Graphics g) {
            //draw border for playingfield
            g.setColor(Color.white);
            g.drawRect(10, 10, 876, 646);

            //draw background for the playingfield
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(11, 11, 875, 645);

            //draw player imageicon
            playerIcon = new ImageIcon("src/images/playerIcon.png");
            playerIcon.paintIcon(this, g, playerX[1], playerY[1]);

        }
    }
}
