import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    public GamePanel() {
        setBackground(Color.BLACK);
    }
    public void callUpdatables(){
        for(GameObject g : gameObjects){
            g.update();
        }
        repaint();
    }

    public boolean addGameObject(GameObject gameObject){
        return gameObjects.add(gameObject);
    }

    public boolean removeGameObject(GameObject gameObject){
        return gameObjects.remove(gameObject);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for(GameObject go : gameObjects){
            Rectangle rect = go.getRectangle();
            g2.drawImage(go.getTexture(),rect.x,rect.y,rect.width, rect.height,this);
        }
    }
}
