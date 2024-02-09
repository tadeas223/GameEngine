package window;

import engine.Engine;
import gameObject.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

import static gameObject.GameObject.getAllObjects;

/**
 * This class draws each {@link GameObject} to the screen.
 */
public class GamePanel extends JPanel {
    private PriorityQueue<GameObject> priorityQueue = new PriorityQueue<>();

    public GamePanel() {
        setDoubleBuffered(true);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

       priorityQueue = new PriorityQueue<>();

       priorityQueue.addAll(getAllObjects(new ArrayList<>(),Engine.getInstance().getGameObjects()));

        for (GameObject go : priorityQueue) {
            if (go.isActive() && go.getTexture() != null) {
                g2.drawImage(go.getTexture(),
                        go.getX(),
                        go.getY(),
                        (int) (go.getWidth() * go.getScaleX()),
                        (int) (go.getHeight() * go.getScaleY()),
                        this);
            }
        }

        g2.dispose();
    }
}
