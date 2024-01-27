package engine;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GlobalKeyListener implements KeyListener {

    private ArrayList<KeyListener> listeners = new ArrayList<>();
    @Override
    public void keyTyped(KeyEvent e) {
        for(KeyListener l : listeners){
            l.keyTyped(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for(KeyListener l : listeners){
            l.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for(KeyListener l : listeners){
            l.keyReleased(e);
        }
    }

    public void addListener(KeyListener listener){
        listeners.add(listener);
    }

    public void removeListener(KeyListener listener){
        listeners.remove(listener);
    }
}
