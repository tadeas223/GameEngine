import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    private GamePanel gamePanel = new GamePanel();
    public GameFrame() {
        add(gamePanel);
    }

    public boolean addGameObject(GameObject gameObject){
        return gamePanel.addGameObject(gameObject);
    }

    public boolean removeGameObject(GameObject gameObject){
        return gamePanel.removeGameObject(gameObject);
    }

    public void callUpdatables(){
        gamePanel.callUpdatables();
    }
}
