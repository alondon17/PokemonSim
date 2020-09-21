package Project.Visuals;

import Project.Battles.PokemonData.Moves.Target;
import Project.SystemStuff.Consts;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class TileData {
    private static HashMap<Integer,Tile> tiles=new HashMap<>();
    static {
        for(Tile tile:Tile.values()){
            tiles.put(tile.val(),tile);
        }
    }
    public enum Tile {

        Empty(-5, 0, 0, 0, SpriteMap.G1TileMap),
        LargeGrass(1, 0, 1, 1, SpriteMap.G1TileMap),
        MountainLeftBack(0, 2, 0, 2, SpriteMap.G1TileMap);
        private int layer;
        private BufferedImage tile;
        private int val;

        Tile(int val, int layer, int x, int y, SpriteMap src) {
            this.layer = layer;
            int tilesize= src.tilesize;
            tile = src.getImg().getSubimage(x *tilesize, y * tilesize, tilesize, tilesize);
            this.val = val;
        }

        public int layer() {
            return layer;
        }

        public BufferedImage tile() {
            return tile;
        }

        public int val() {
            return val;
        }
    }

    enum SpriteMap {
        G1TileMap("/images/gen1Spritemap.png",16);
        private BufferedImage img;
private int tilesize;
        SpriteMap(String url,int tileSize) {
            img = GetImages.getResource(url);
        this.tilesize=tileSize;
        }

        public BufferedImage getImg() {
            return img;
        }
    }
    public static BufferedImage getImg(int val){
        return tiles.get(val).tile();
    }
    public static int getLayer(int val){
        Tile tile = tiles.get(val);
        return tile!=null?tile.layer():0;
    }
}
