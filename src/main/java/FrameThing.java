import javax.swing.*;
import java.awt.*;

public class FrameThing extends JFrame {
    public Playingfield playingfield;
    public FrameThing() {
        playingfield = new Playingfield(Consts.typeChart,this);
        this.setBounds(0, 0, 400, 400);
        this.setBackground(Color.GRAY);
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setFocusable(true);

        addKeyListener(playingfield);
        this.add(playingfield);


    }




}
