import javax.swing.*; // JFrame, JPanel, ...
import java.awt.*; // GridLayout

public class Random10x10 extends JFrame { // This is the window class
    public Random10x10(double[][] tilemap) {
        this.setLayout(new GridLayout(10, 10)); // This makes the frame into a 10 x 10 grid
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) this.add(new RandomNumber(i, j,tilemap)); // This puts all 100 numbers in place
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

    public static class RandomNumber extends JPanel { // This is the random number grid space class
        public RandomNumber(int a, int b, double[][] tilemap) {
            JTextArea area = new JTextArea(); // This will contain the number
            area.setText(switch ((int) (tilemap[a][b] * 2)) {
                case 1 -> ConsoleColors.RED + ((char) 9632) + ConsoleColors.RESET;

                case 4 -> ConsoleColors.GREEN + ((char) 9632) + ConsoleColors.RESET;
                case 0 -> ConsoleColors.PURPLE + ((char) 9632) + ConsoleColors.RESET;

                default -> ConsoleColors.BLACK + ((char) 9632) + ConsoleColors.RESET;

            }); // This puts the number in place

            area.setEditable(false); // This prevents the user from changing the matrix
            this.add(area); // This puts the number into the gridspace

        }
    }
}