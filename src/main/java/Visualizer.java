import java.awt.event.KeyEvent;
import java.util.EventListener;

public class Visualizer {

    final static int aRenderDis = 12;
    final static int bRenderDis = 12;

    public static void render(int[][] tilemap, int aLoc, int bLoc) {
        int aLength = tilemap.length;
        int bLength = tilemap[0].length;
        for (int a = aLoc - aRenderDis; a <= aLoc + aRenderDis; a++) {
            for (int b = bLoc - bRenderDis; b <= bLoc + bRenderDis; b++) {
                if (a < 0 || aLength <= a || b < 0 || bLength <= b)
                    System.out.print(" ");
                else {
                    switch (tilemap[a][b]) {
                        case 1:
                            System.out.print(ConsoleColors.RED + ((char) 9632) + ConsoleColors.RESET);
                    }
                }
            }
            System.out.println();
        }
    }

    public static void render(double[][] tilemap, int aLoc, int bLoc) {
        int aLength = tilemap.length;
        int bLength = tilemap[0].length;
        for (int a = aLoc - aRenderDis; a <= aLoc + aRenderDis; a++) {
            for (int b = bLoc - bRenderDis; b <= bLoc + bRenderDis; b++) {
                if (a < 0 || aLength <= a || b < 0 || bLength <= b)
                    System.out.print(" ");
                else {
                    switch ((int) (tilemap[a][b] * 2)) {
                        case 1:
                            System.out.print(ConsoleColors.RED + ((char) 9632) + ConsoleColors.RESET);
                            break;
                        case 4:
                            System.out.print(ConsoleColors.GREEN + ((char) 9632) + ConsoleColors.RESET);
                            break;
                        case 0:
                            System.out.print(ConsoleColors.PURPLE + ((char) 9632) + ConsoleColors.RESET);
                            break;
                        default:
                            System.out.print(ConsoleColors.BLACK + ((char) 9632) + ConsoleColors.RESET);
                            break;
                    }

                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
