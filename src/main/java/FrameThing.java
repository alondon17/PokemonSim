import javax.swing.*;
import java.awt.*;

public class FrameThing extends JFrame {
    public FrameThing() {
        Playingfield playingfield = new Playingfield(Consts.typeChart);
        this.setBounds(0, 0, 900, 900);
        this.setBounds(0, 0, 900, 900);
        this.setBackground(Color.GRAY);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(playingfield);
        playingfield.addKeyListener();
    }




}
