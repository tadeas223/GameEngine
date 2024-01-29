package engine;

import gameObject.GameObject;
import window.GameFrame;

import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * A singleton class that creates and handles the game.
 * This class holds each {@linkplain GameObject} in the scene,
 * and handles the game loop.
 */
public class Engine implements Runnable {
    // global instance of singleton
    private static Engine instance = null;

    //final variables
    private final ArrayList<GameObject> gameObjects = new ArrayList<>();
    private final GlobalKeyListener globalKeyListener = new GlobalKeyListener();

    // Changeable variables
    private int fps = 60;
    private GameFrame frame;


    // Internal variables
    private float deltaTime = 0;
    private Thread thread = null;
    private Double drawInterval = (double) (1000000000 / fps);

    /**
     * This constructor is a private constructor
     * that ensures the singleton instance of this class is created only by itself.
     * It does not perform any additional actions.
     */
    private Engine() {
    }

    //region singleton

    /**
     * Returns the static instance of this singleton.
     * If this class has never been instantiated before,
     * it creates a new instance of it.
     *
     * @return The instance of the engine.
     */
    public static Engine getInstance() {
        if (instance == null) {
            instance = new Engine();
            instance.construct();
        }
        return instance;
    }

    /**
     * This method is a constructor for this class.
     * It is included because when this class is instantiated,
     * some of the objects instantiated within this class require a reference to this class
     * that was still not created - causing an error.
     */
    private void construct() {
        frame = new GameFrame();
        frame.addKeyListener(globalKeyListener);
        thread = new Thread(this);
        thread.start();
    }

    //endregion

    /**
     * Calls an update method in every {@linkplain GameObject} added to this class.
     */
    private void callUpdate() {
        for (GameObject g : gameObjects) {
            if(g.isActive()){
                g.update(deltaTime);
            }
        }
    }

    //region Get&Set

    /**
     * Adds a {@linkplain GameObject} to the scene.
     *
     * @param gameObject that needs to be added to the scene.
     */
    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    /**
     * Removes the {@linkplain GameObject} from the scene.
     *
     * @param gameObject that needs to be removed from the scene.
     */
    public void removeGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void addKeyListener(KeyListener listener) {
        globalKeyListener.addListener(listener);
    }

    public void removeKeyListener(KeyListener listener) {
        globalKeyListener.removeListener(listener);
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
        drawInterval = (double) (1000000000 / fps);
    }
    //endregion

    /**
     * Creates an update loop for the game,
     * The duration of each iteration if the loop is determined by the fps variable.
     */
    @Override
    public void run() {
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (thread != null) {
            long time = System.currentTimeMillis();

            frame.repaint();

            callUpdate();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            deltaTime = (float) (System.currentTimeMillis() - time) / 1000;
        }
    }
}
