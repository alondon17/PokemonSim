package Project.Visuals;

import Project.Battles.BattleManager;
import Project.Battles.Opponent;
import Project.Battles.Player;
import Project.Battles.TrainerClass;
import Project.Battles.PokemonData.PokeSelector;
import Project.Battles.PokemonData.Pokemon;
import Project.Battles.PokemonData.SpeciesList;
import Project.SystemStuff.Consts;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Playingfield extends JLayeredPane implements KeyListener {
    private static BufferedImage playerSpriteMap=GetImages.getPlayerSpriteMap();

    public static BufferedImage getBrownWithGrassTile() {
        return brownWithGrassTile;
    }

    private static BufferedImage brownWithGrassTile=GetImages.getBrownWithGrassTile();




    int offsetx = 0;
    int offsety = 0;
    JFrame parent;
    int sizex = Consts.xTileSize;
    int sizey = Consts.yTileSize;
    int xLoc = 0;

    public BufferedImage getPlayerSprite() {
        return playerSprite;
    }

    public static BufferedImage getPlayerSpriteMap() {
        return playerSpriteMap;
    }

    public int getxLoc() {
        return xLoc;
    }

    public int getyLoc() {
        return yLoc;
    }

    int yLoc = 0;
    double[][] tilemap;
    Color bordo = new Color(120, 0, 0);
    Color lightred = new Color(250, 128, 114);
    boolean isBusy = false;
    private BufferedImage playerSprite;
    private HashMap<Integer, ArrayList<Runnable>> layers = new HashMap<>();
    private HashMap<Integer, JPanel> layerMap = new HashMap<>();

    public Playingfield(double[][] tilemap, JFrame parent) {
        super();
        playerSprite = playerSpriteMap.getSubimage(0, 0, 32, 32);
        this.tilemap = tilemap;
        this.parent = parent;
        setSize(sizex * Consts.aRenderDis * 2, sizey * Consts.bRenderDis * 2);
        JPanel newLayer = new PlayLayer(tilemap,this,2);
        newLayer.setBounds(0, 0, sizex * Consts.aRenderDis * 2, sizey * Consts.bRenderDis * 2);
        this.add(newLayer, JLayeredPane.DEFAULT_LAYER,0);
        layerMap.put(2, newLayer);

        newLayer = new PlayLayer(tilemap,this,1);
        newLayer.setBounds(0, 0, sizex * Consts.aRenderDis * 2, sizey * Consts.bRenderDis * 2);
        this.add(newLayer, JLayeredPane.DEFAULT_LAYER,1);
        layerMap.put(1, newLayer);
        newLayer = new PlayLayer(tilemap,this,0);
        newLayer.setBounds(0, 0, sizex * Consts.aRenderDis * 2, sizey * Consts.bRenderDis * 2);
        this.add(newLayer, JLayeredPane.DEFAULT_LAYER,2);
        layerMap.put(0, newLayer);






    }

    public boolean isWall(double val) {
        return val == 2;
    }

    public boolean isBattle(double val) {
        return val == 0.5;
    }

    public void moveChar(int x, int y) {
        if (y + yLoc >= 0 && y + yLoc < tilemap.length && x + xLoc >= 0 && x + xLoc < tilemap[0].length && !isWall(tilemap[y + yLoc][x + xLoc])) {
            yLoc += y;
            xLoc += x;
        }
        this.repaint();
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
//        doMoveActionRender(g);

paintChildren(g);

    }

//    public void doMoveActionRender(Graphics g) {
//
//        int yLength = tilemap.length;
//        int xLength = tilemap[0].length;
//        int xPlayer = -999;
//        int yPlayer = -999;
//        int yRenderloc = 0;
//        for (int y = yLoc > Consts.aRenderDis ? yLoc - Consts.aRenderDis : 0; yLoc > Consts.aRenderDis ? y <= yLoc + Consts.aRenderDis : y <= 2 * Consts.aRenderDis; y++) {
//            int xRenderloc = 0;
//            for (int x = xLoc > Consts.bRenderDis ? xLoc - Consts.bRenderDis : 0; x <= (xLoc > Consts.bRenderDis ? xLoc + Consts.bRenderDis : 2 * Consts.bRenderDis); x++) {
//                Color color = null;
//                if (y == yLoc && x == xLoc) {
//                    xPlayer = xRenderloc;
//                    yPlayer = yRenderloc;
//                }
//                if (y < 0 || yLength <= y || x < 0 || xLength <= x)
//                    color = Color.GRAY;
//                else {
//                    switch ((int) (tilemap[y][x] * 2)) {
//                        case 1 -> {
//                            int finalXRenderloc = xRenderloc;
//                            int finalYRenderloc = yRenderloc;
//
//                            drawImage(brownWithGrassTile, offsetx + finalXRenderloc * sizex, offsety + finalYRenderloc * sizey, sizex, sizey, 1);
//
//                        }
//                        case 0 -> color = (bordo);
//                        case 2 -> color = (Color.WHITE);
//                        case 4 -> color = (Color.GREEN);
//                    }
//                }
//                if (color != null) {
//                    drawRect(color,offsetx + xRenderloc * sizex, offsety + yRenderloc * sizey, sizex, sizey,1);
//                }
//                xRenderloc++;
//            }
//            yRenderloc++;
//
//        }
//        if (xPlayer != -999) {
//            int finalXPlayer = xPlayer;
//            int finalYPlayer = yPlayer;
//
//                drawImage(playerSprite, offsetx + finalXPlayer * sizex, offsety + finalYPlayer * sizey, sizex, sizey, 1);
//
//        }
//
//
//    }
//
//    private void drawImage(BufferedImage img, int x, int y, int sizex, int sizey, int layer) {
//        if (!layerMap.containsKey(layer)) {
//            JPanel newLayer = new JPanel();
//
//            newLayer.setBounds(0, 0, sizex * Consts.aRenderDis * 2, sizey * Consts.bRenderDis * 2);
//            layerMap.put(layer, newLayer);
//            add(newLayer, layer);
//        }
//        layerMap.get(layer).getGraphics().drawImage(img, x, y, sizex, sizey, layerMap.get(layer));
//    }
//
//    private void drawRect(Color color, int x, int y, int sizex, int sizey, int layer) {
//        if (!layerMap.containsKey(layer)) {
//            JPanel newLayer = new JPanel();
//
//            newLayer.setBounds(0, 0, sizex * Consts.aRenderDis * 2, sizey * Consts.bRenderDis * 2);
//            layerMap.put(layer, newLayer);
//            add(newLayer, layer);
//        }
//        Graphics g=layerMap.get(layer).getGraphics();
//        g.setColor(color);
//        layerMap.get(layer).getGraphics().fillRect(x, y, sizex, sizey);
//    }

    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (!isBusy) {
            isBusy = true;

            doMovement(e);

        }

    }

    public void doMovement(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN -> {
                moveChar(0, 1);
                playerSprite = playerSpriteMap.getSubimage(0, 0, 32, 32);
            }
            case KeyEvent.VK_UP -> {
                moveChar(0, -1);
                playerSprite = playerSpriteMap.getSubimage(0, 96, 32, 32);
            }
            case KeyEvent.VK_LEFT -> {
                moveChar(-1, 0);
                playerSprite = playerSpriteMap.getSubimage(0, 32, 32, 32);
            }
            case KeyEvent.VK_RIGHT -> {
                moveChar(1, 0);
                playerSprite = playerSpriteMap.getSubimage(0, 64, 32, 32);
            }
        }
        this.repaint();
        System.out.println(e.getKeyCode());
//        doMoveActionRender(getGraphics());
        if (isBattle(tilemap[yLoc][xLoc])) {
            this.setVisible(false);
            BattleManager battleManager = new BattleManager(new Player("alon", PokeSelector.select()), new Opponent(TrainerClass.BIKER, "Bob", new Pokemon[]{SpeciesList.getPoke(1, 1)}, 50), 1);
            try {
                if (battleManager.startBattle()) {
                    System.out.println("Success");
                } else {
                    xLoc = 0;
                    yLoc = 0;
                }
            } catch (Exception exception) {
                xLoc = 0;
                yLoc = 0;
            } finally {
                //doMoveActionRender(getGraphics());
            }
            setVisible(true);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        isBusy = false;
    }

}