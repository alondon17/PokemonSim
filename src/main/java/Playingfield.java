import javax.swing.*;
import java.awt.*;

public class Playingfield extends JPanel {
    int offsetx = 100;
    int offsety = 100;
    int sizex = 10;
    int sizey = 10;
    int bLoc = 9;
    int aLoc = 9;
    double[][] tilemap;
    private ImageIcon playerIcon;
    private int[][] playinggrid = new int[10][10];
    private int[] playerX = new int[10];
    private int[] playerY = new int[10];
    Color bordo=new Color(120,0,0);
    Color lightred=new Color(250,128,114);
    public Playingfield(double[][] tilemap) {
        this.tilemap = tilemap;

    }
    public static void moveChar(int x, int y) {

    }
    public void paint(Graphics g) {
        //draw border for playingfield
        g.setColor(Color.white);
        g.drawRect(10, 10, 876, 646);

        //draw background for the playingfield
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(11, 11, 875, 645);

        //draw player imageicon
//            playerIcon = new ImageIcon("src/images/playerIcon.png");
//            playerIcon.paintIcon(this, g, playerX[1], playerY[1]);
        int aLength = tilemap.length;
        int bLength = tilemap[0].length;
        for (int a = aLoc - Consts.aRenderDis; a <= aLoc + Consts.aRenderDis; a++) {
            for (int b = bLoc - Consts.bRenderDis; b <= bLoc + Consts.bRenderDis; b++) {
                if (a == aLoc && b == bLoc) g.setColor(Color.MAGENTA);
                else if (a < 0 || aLength <= a || b < 0 || bLength <= b)
                    g.setColor(Color.GRAY);
                else {
                    switch ((int) (tilemap[a][b] * 2)) {
                        case 1 -> g.setColor(lightred);
                        case 0 -> g.setColor(bordo);
                        case 2 -> g.setColor(Color.WHITE);
                        case 4 -> g.setColor(Color.GREEN);
                    }
                }
                g.fillRect(offsetx + b * sizex, offsety + a * sizey, sizex, sizey);
            }
        }
    }
}