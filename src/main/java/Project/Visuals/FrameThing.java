package Project.Visuals;

import Project.SystemStuff.Consts;

import javax.swing.*;
import java.awt.*;

public class FrameThing extends JFrame {
    public Playingfield playingfield;

    public FrameThing() {
        super("pokemon");
        this.setSize(Consts.xTileSize*Consts.aRenderDis*2+16,Consts.yTileSize*Consts.bRenderDis*2+39);
        playingfield = new Playingfield(Consts.typeChart, this);
        JLayeredPane layeredPane = new JLayeredPane();
        this.addKeyListener(playingfield);


        layeredPane.add(playingfield, JLayeredPane.DEFAULT_LAYER);

        this.setBackground(Color.GRAY);
        this.setResizable(true);
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);
        this.add(layeredPane);

    }


}
