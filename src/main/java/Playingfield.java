import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Playingfield extends JPanel implements KeyListener {
    int offsetx = 0;
    int offsety = 0;
    JFrame parent;
    int sizex = 40;
    int sizey = 40;
    int xLoc = 0;
    int yLoc = 0;
    double[][] tilemap;
    Color bordo = new Color(120, 0, 0);
    Color lightred = new Color(250, 128, 114);
    boolean isBusy = false;
    BufferedImage image;
    private ImageIcon playerIcon;
    private int[][] playinggrid = new int[10][10];
    private int[] playerX = new int[10];
    private int[] playerY = new int[10];
    private BufferedImage playerSprite;
    private static BufferedImage playerSpriteMap;

    static {
        try {
            playerSpriteMap = ImageIO.read(new File("src/main/java/spriteMap.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage brownWithGrassTile;

    static {
        try {
            brownWithGrassTile = ImageIO.read(new File("src/main/java/grassTile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Image playerTile = Toolkit.getDefaultToolkit().getImage("src/main/java/pokemon.png");
    public Playingfield(double[][] tilemap, JFrame parent) {
        super();
        this.tilemap = tilemap;
        this.parent = parent;
        playerSprite=playerSpriteMap.getSubimage(0,0,32,32);

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
int xPlayer=-999;
int yPlayer=-999;
        int yRenderloc = 0;
        for (int y = yLoc - Consts.aRenderDis; y <= yLoc + Consts.aRenderDis; y++) {
            int xRenderloc = 0;
            for (int x = xLoc - Consts.bRenderDis; x <= xLoc + Consts.bRenderDis; x++) {
                Color color=null;
                if (y == yLoc && x == xLoc) {
                    xPlayer=xRenderloc;
                    yPlayer=yRenderloc;
                }if (y < 0 || yLength <= y || x < 0 || xLength <= x)
                    color = Color.GRAY;
                else {
                    switch ((int) (tilemap[y][x] * 2)) {
                        case 1 -> g.drawImage(brownWithGrassTile, offsetx + xRenderloc * sizex, offsety + yRenderloc * sizey, sizex, sizey, this);

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
        if(xPlayer!=-999)
        g.drawImage(playerSprite, offsetx + xPlayer * sizex, offsety + yPlayer * sizey, sizex, sizey, this);

    }


    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (!isBusy) {
            isBusy = true;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN -> {moveChar(0, 1);
                    playerSprite=playerSpriteMap.getSubimage(0,0,32,32);}
                case KeyEvent.VK_UP -> {moveChar(0, -1);playerSprite=playerSpriteMap.getSubimage(0,96,32,32);}
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