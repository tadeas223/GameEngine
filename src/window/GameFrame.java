package window;

import engine.Engine;

import javax.swing.*;


public class GameFrame extends JFrame {
    private Engine engine;

    private GamePanel gamePanel = new GamePanel();

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public GameFrame(){
        engine = Engine.getInstance();

        addWindowListener(new ExitOnClose());

        add(gamePanel);

        setSize(500,500);
        setVisible(true);
        setFocusable(true);
        requestFocus();

    }
}
