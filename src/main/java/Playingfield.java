import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Playingfield extends JPanel implements KeyListener {
    int offsetx = 0;
    int offsety = 0;
    JFrame parent;
    int sizex = 10;
    int sizey = 10;
    int xLoc = 9;
    int yLoc = 9;
    double[][] tilemap;
    Color bordo = new Color(120, 0, 0);
    Color lightred = new Color(250, 128, 114);
    boolean isBusy = false;
    BufferedImage image;
    private ImageIcon playerIcon;
    private int[][] playinggrid = new int[10][10];
    private int[] playerX = new int[10];
    private int[] playerY = new int[10];
    private Image tile1;
    private Image tile2;
    public Playingfield(double[][] tilemap, JFrame parent) {
        super();
        this.tilemap = tilemap;
        this.parent = parent;
        try {
            File pathToFile = new File("./image.png");
            tile1 = ImageIO.read(pathToFile);
        } catch (Exception ex) {
            System.out.println("oh");
        }
        tile2=Toolkit.getDefaultToolkit().getImage("image.png");
    }

    public boolean isWall(double val) {
        return val == 2;
    }

    public void moveChar(int x, int y) {
        if (y+yLoc<0||y+yLoc>=tilemap.length||x+xLoc<0||x+xLoc>=tilemap[0].length||!isWall(tilemap[y + yLoc][x + xLoc])) {
            yLoc += y;
            xLoc += x;
            this.repaint();
            this.paint(getParent().getGraphics());
            this.invalidate();
            parent.revalidate();
        }

    }

    public void paint(Graphics g) {
////        g.clearRect();
//        //draw border for playingfield
//        g.setColor(Color.white);
//        g.drawRect(10, 10, 876, 646);
//
//        //draw background for the playingfield
//        g.setColor(Color.LIGHT_GRAY);
//        g.fillRect(11, 11, 875, 645);

        //draw player imageicon
//            playerIcon = new ImageIcon("src/images/playerIcon.png");
//            playerIcon.paintIcon(this, g, playerX[1], playerY[1]);
        doMoveActionRender(g);

    }

    public void doMoveActionRender(Graphics g) {
        int yLength = tilemap.length;
        int xLength = tilemap[0].length;

        int yRenderloc = 0;
        for (int y = yLoc - Consts.aRenderDis; y <= yLoc + Consts.aRenderDis; y++) {
            int xRenderloc = 0;
            for (int x = xLoc - Consts.bRenderDis; x <= xLoc + Consts.bRenderDis; x++) {
                Color color=null;
                if (y == yLoc && x == xLoc)
                    g.drawImage(tile2, offsetx + xRenderloc * sizex, offsety + yRenderloc * sizey, sizex, sizey, this);
                else if (y < 0 || yLength <= y || x < 0 || xLength <= x)
                    color = Color.GRAY;
                else {
                    switch ((int) (tilemap[y][x] * 2)) {
                        case 1 -> color = (lightred);
                        case 0 -> color = (bordo);
                        case 2 -> color = (Color.WHITE);
                        case 4 -> color = (Color.GREEN);
                    }
                }
                if (color != null) {
                    g.setColor(color);
                    g.fillRect(offsetx + xRenderloc * sizex, offsety + yRenderloc * sizey, sizex, sizey);
                }
                xRenderloc++;
            }
            yRenderloc++;

        }

    }


    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (!isBusy) {
            isBusy = true;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN -> moveChar(0, 1);
                case KeyEvent.VK_UP -> moveChar(0, -1);
                case KeyEvent.VK_LEFT -> moveChar(-1, 0);
                case KeyEvent.VK_RIGHT -> moveChar(1, 0);
            }
            System.out.println(e.getKeyCode());
            doMoveActionRender(getGraphics());
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        isBusy = false;
    }
}