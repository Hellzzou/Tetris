import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardListener implements KeyListener {
    private Window window;

    KeyBoardListener(Window window) {
        this.window = window;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ( e.getKeyCode() == KeyEvent.VK_LEFT){
            if ( window.getAnimation().getGamePanel().nothingLeft()) window.getAnimation().getGamePanel().moveLeft();
        }
        if ( e.getKeyCode() == KeyEvent.VK_RIGHT){
            if ( window.getAnimation().getGamePanel().nothingRight()) window.getAnimation().getGamePanel().moveRight();
        }
        if ( e.getKeyCode() == KeyEvent.VK_DOWN){
            window.getAnimation().setSleep((300 - ((window.getAnimation().getLevel() - 1) * 25)) / 3);
        }
        if ( e.getExtendedKeyCode() == KeyEvent.VK_UP){
            window.getAnimation().getGamePanel().rotate();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if ( e.getKeyCode() == KeyEvent.VK_DOWN){
            window.getAnimation().setSleep(300 - ((window.getAnimation().getLevel() - 1) * 25));
        }
    }
}
