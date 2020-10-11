package Project.Visuals;

import Project.SystemStuff.Consts;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayLayer extends JPanel {
    Color bordo = new Color(120, 0, 0);
    private Playingfield parent;
    private double[][] tilemap;
    private int layer;

    public PlayLayer(double[][] tilemap, JComponent parent, int layer) {
        this.parent = (Playingfield) parent;
        this.tilemap = tilemap;
        this.layer = layer;
    }

    public void paintComponent(Graphics g) {
        int yLoc = parent.getyLoc();
        int xLoc = parent.getxLoc();
        BufferedImage brownWithGrassTile = Playingfield.getBrownWithGrassTile();
        int yLength = tilemap.length;
        int xLength = tilemap[0].length;
        int xPlayer = -999;
        int yPlayer = -999;
        int sizex = Consts.xTileSize;
        int sizey = Consts.yTileSize;
        int offsetx = 0;
        int offsety = 0;
        int xRendStart=xLoc > Consts.bRenderDis ? xLoc - Consts.bRenderDis : 0;
        int xRendEnd=xLoc > Consts.bRenderDis ? xLoc + Consts.bRenderDis : 2 * Consts.bRenderDis;
        int yRendStart = yLoc > Consts.aRenderDis ? yLoc - Consts.aRenderDis : 0;
        int yRendEnd = yLoc > Consts.aRenderDis ? yLoc + Consts.aRenderDis : 2 * Consts.aRenderDis
        int yRenderloc = 0;
        if (layer != 1)
            for (int y = yRendStart; y <= yRendEnd; y++) {
                int xRenderloc = 0;
                for (int x = xRendStart; x <= xRendEnd; x++) {
                    if (!(y < 0 || yLength <= y || x < 0 || xLength <= x) && isSameLayer(tilemap[y][x])) {
                        Color color = null;
                        BufferedImage tile = null;
                        switch ((int) (tilemap[y][x] * 2)) {
                            case 1 -> {
                                tile = brownWithGrassTile;
                            }
//                            case 0 -> color = (bordo);
                            case 0 -> tile = TileData.getImg(0);
                            case 2 -> color = (Color.WHITE);
                            case 4 -> color = (Color.GREEN);
                        }

                        if (color != null) {
                            g.setColor(color);
                            g.fillRect(offsetx + xRenderloc * sizex, offsety + yRenderloc * sizey, sizex, sizey);
                        } else {
                            g.drawImage(tile, offsetx + xRenderloc * sizex, offsety + yRenderloc * sizey, sizex, sizey, this);

                        }
                    }
                    xRenderloc++;
                }
                yRenderloc++;

            }
        else {
//            for (int y = yRendStart; y <= yRendEnd; y++) {
//                int xRenderloc = 0;
//                for (int x = xRendStart; x <= xRendEnd; x++) {
//
//                    if (y == yLoc && x == xLoc) {
//                        xPlayer = xRenderloc;
//                        yPlayer = yRenderloc;
//                        break;
//                    }
//                    xRenderloc++;
//                }
//                yRenderloc++;
//            }
//            if()
            //TODO redefine function for multiple charater and be betteer
            g.drawImage(parent.getPlayerSprite(), offsetx + xPlayer * sizex, offsety + yPlayer * sizey, sizex, sizey, this);


        }

    }


    private boolean isSameLayer(double v) {
        if (tilemap == Consts.typeChart) v *= 2;
        return layer == TileData.getLayer((int) v);
    }
}
