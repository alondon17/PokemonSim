package Project.Visuals;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GetImages {
    //        ClassLoader classLoader = GetImages.class.getClassLoader();
//
//        File file = new File(classLoader.getResource("/resources/images/spriteMap.png").getFile());


    private static BufferedImage playerSpriteMap = getResource("/images/spriteMap.png");


    //            playerSpriteMap = ImageIO.read(new File("/resources/images/spriteMap.png"));

    private static BufferedImage brownWithGrassTile = getResource("/images/grassTile.png");
    private static BufferedImage gen1SpriteMap=getResource("/images/gen1Spritemap.png");

    public static BufferedImage getGen1SpriteMap() {
        return gen1SpriteMap;
    }

    public static BufferedImage getPlayerSpriteMap() {
        return playerSpriteMap;
    }

    static {


        //            brownWithGrassTile = ImageIO.read(new File("/resources/images/grassTile.png"));


    }

    public static BufferedImage getBrownWithGrassTile() {
        return brownWithGrassTile;
    }
    public static BufferedImage getResource(String URL){
        try {
            return ImageIO.read(GetImages.class.getResource(URL));
        } catch (Exception e) {
            e.printStackTrace();
            try {
                return ImageIO.read(GetImages.class.getResource("/images/errorTile.png"));
            } catch ( Exception ioException) {
                ioException.printStackTrace();
                return new BufferedImage(32,32,1);
            }
        }
    }
}
