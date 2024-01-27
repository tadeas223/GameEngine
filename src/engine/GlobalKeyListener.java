package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * A key listener that forwards each key event call to
 * the listeners that can be added into this class.
 * Is used with {@linkplain Engine} for global key handling.
 *
 * @see Engine
 */
public class GlobalKeyListener implements KeyListener {

    private final ArrayList<KeyListener> listeners = new ArrayList<>();

    @Override
    public void keyTyped(KeyEvent e) {
        for (KeyListener l : listeners) {
            l.keyTyped(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (KeyListener l : listeners) {
            l.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (KeyListener l : listeners) {
            l.keyReleased(e);
        }
    }

    public void addListener(KeyListener listener) {
        listeners.add(listener);
    }

    public void removeListener(KeyListener listener) {
        listeners.remove(listener);
    }
}
