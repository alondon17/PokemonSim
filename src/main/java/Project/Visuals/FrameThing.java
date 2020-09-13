package Project.Visuals;

import Project.SystemStuff.Consts;

import javax.swing.*;
import java.awt.*;

public class FrameThing extends JFrame {
    public Playingfield playingfield;
    public FrameThing() {
        playingfield = new Playingfield(Consts.typeChart,this);
        addKeyListener(playingfield);
        this.add(playingfield);this.setBounds(0, 0, 600, 600);
        this.setBackground(Color.GRAY);
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setFocusable(true);




    }




}
