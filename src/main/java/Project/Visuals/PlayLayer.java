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

        int yRenderloc = 0;
        if (layer != 1)
            for (int y = yLoc > Consts.aRenderDis ? yLoc - Consts.aRenderDis : 0; yLoc > Consts.aRenderDis ? y <= yLoc + Consts.aRenderDis : y <= 2 * Consts.aRenderDis; y++) {
                int xRenderloc = 0;
                for (int x = xLoc > Consts.bRenderDis ? xLoc - Consts.bRenderDis : 0; x <= (xLoc > Consts.bRenderDis ? xLoc + Consts.bRenderDis : 2 * Consts.bRenderDis); x++) {
                    if (!(y < 0 || yLength <= y || x < 0 || xLength <= x) && isSameLayer(tilemap[y][x])) {
                        Color color = null;
                        switch ((int) (tilemap[y][x] * 2)) {
                            case 1 -> {
                                g.drawImage(brownWithGrassTile, offsetx + xRenderloc * sizex, offsety + yRenderloc * sizey, sizex, sizey, this);
                            }
                            case 0 -> color = (bordo);
                            case 2 -> color = (Color.WHITE);
                            case 4 -> color = (Color.GREEN);
                        }

                        if (color != null) {
                            g.setColor(color);
                            g.fillRect(offsetx + xRenderloc * sizex, offsety + yRenderloc * sizey, sizex, sizey);
                        }
                    }
                    xRenderloc++;
                }
                yRenderloc++;

            }
        else {
            for (int y = yLoc > Consts.aRenderDis ? yLoc - Consts.aRenderDis : 0; yLoc > Consts.aRenderDis ? y <= yLoc + Consts.aRenderDis : y <= 2 * Consts.aRenderDis; y++) {
                int xRenderloc = 0;
                for (int x = xLoc > Consts.bRenderDis ? xLoc - Consts.bRenderDis : 0; x <= (xLoc > Consts.bRenderDis ? xLoc + Consts.bRenderDis : 2 * Consts.bRenderDis); x++) {

                    if (y == yLoc && x == xLoc) {
                        xPlayer = xRenderloc;
                        yPlayer = yRenderloc;
                        break;
                    }
                }
                xRenderloc++;
            }
            yRenderloc++;

        }
        g.drawImage(parent.getPlayerSprite(), offsetx + xPlayer * sizex, offsety + yPlayer * sizey, sizex, sizey, this);

    }


    private boolean isSameLayer(double v) {
        return layer == switch ((int) (v * 2)) {
            case 1, 0 -> 2;
            default -> 0;
        };
    }
}
