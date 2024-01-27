package window;

import javax.swing.*;


/**
 * The only purpose of this class is to hold a {@link GamePanel},
 * because a {@link GamePanel} cant create its own window,
 * and this class cant draw anything on the screen.
 */
public class GameFrame extends JFrame {
    private final GamePanel gamePanel = new GamePanel();

    public GameFrame() {
        add(gamePanel);

        setSize(500, 500);
        setVisible(true);
        setFocusable(true);
        requestFocus();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
