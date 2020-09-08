import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

public class Visualizer {
    static int aLength;
    static int bLength;
static int aLoc;
static int bLoc;
static byte[][] tilemap;
static JFrame frame;
    public static void main(String[] args) {
        frame = new FrameThing();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 3
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH) ;// 6
        frame.setVisible(true);


    }

    public static void game(byte[][] tilemap,int aLoc, int bLoc, int aLocEnd, int bLocEnd) {
        Visualizer.tilemap=tilemap;
        Visualizer.aLoc=aLoc;
        Visualizer.bLoc=bLoc;
        //while (aLoc != aLocEnd && bLoc != bLocEnd)
        {
render();
        }

    }

    public static void render() {
        aLength = tilemap.length;
        bLength = tilemap[0].length;
        for (int a = aLoc - Consts.aRenderDis; a <= aLoc + Consts.aRenderDis; a++) {
            for (int b = bLoc - Consts.bRenderDis; b <= bLoc + Consts.bRenderDis; b++) {
                if (a==aLoc&&b==bLoc)System.out.print(ConsoleColors.PURPLE + ((char) 9632) + ConsoleColors.RESET);
                else if (a < 0 || aLength <= a || b < 0 || bLength <= b)
                    System.out.print(" ");
                else {
                    switch (tilemap[a][b]) {
                        case 1 -> System.out.print(ConsoleColors.RED + ((char) 9632) + ConsoleColors.RESET);
                        case 0 -> System.out.print(ConsoleColors.BLUE + ((char) 9632) + ConsoleColors.RESET);
                        case 2 -> System.out.print(ConsoleColors.GREEN + ((char) 9632) + ConsoleColors.RESET);
                        default -> System.out.print(ConsoleColors.BLACK + ((char) 9632) + ConsoleColors.RESET);
                    }
                }
            }
            System.out.println();
        }
    }

    public static void render(double[][] tilemap, int aLoc, int bLoc) {
        int aLength = tilemap.length;
        int bLength = tilemap[0].length;
        for (int a = aLoc - Consts.aRenderDis; a <= aLoc + Consts.aRenderDis; a++) {
            for (int b = bLoc - Consts.bRenderDis; b <= bLoc + Consts.bRenderDis; b++) {
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
