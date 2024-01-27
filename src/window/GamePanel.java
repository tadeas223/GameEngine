package window;

import engine.Engine;
import gameObject.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.PriorityQueue;

public class GamePanel extends JPanel {
    Engine engine = Engine.getInstance();

    public GamePanel(){
        setDoubleBuffered(true);
        setVisible(true);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        PriorityQueue<GameObject> priorityQueue = new PriorityQueue<>();

        for(GameObject gameObject : engine.getGameObjects()){
            priorityQueue.add(gameObject);
            for(GameObject child : gameObject.getChildren()){
                priorityQueue.add(child);
            }
        }

        Iterator iterator = priorityQueue.iterator();

        while(iterator.hasNext()){
            GameObject go = (GameObject) iterator.next();
            if(go.isActive()){
                Rectangle rect = go.getRectangle();
                g2.drawImage(go.getTexture(),rect.x,rect.y,
                        (int)(rect.width*go.getScale().x),
                        (int)(rect.height*go.getScale().y),
                        this);
            }
        }

        g2.dispose();
    }
}
