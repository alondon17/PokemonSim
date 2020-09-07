import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MoveKeyListen implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_DOWN-> Playingfield.moveChar(0,1);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
