package engine;

import gameObject.GameObject;
import window.GameFrame;
import window.GamePanel;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Engine implements Runnable{
    private static Engine instance = null;
    public static final int FPS = 60;

    private GameFrame frame;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private float deltaTime = 0;
    private Thread thread = null;
    private GlobalKeyListener globalKeyListener = new GlobalKeyListener();

    private Engine() {}

    private void callUpdatables(){
        for(Updatable u : gameObjects){
            u.update(deltaTime);
        }
    }

    //region singleton
    public static Engine getInstance(){
        if(instance == null) {
            instance = new Engine();
            instance.construct();
        }
        return instance;
    }

    public static Engine resetInstance(){
        instance = new Engine();
        instance.construct();
        return instance;
    }

    private void construct(){
        frame = new GameFrame();
        frame.addKeyListener(globalKeyListener);
        thread = new Thread(this);
        thread.start();
    }


    //endregion


    //region Get&Set
    public boolean addGameObject(GameObject gameObject){
        return gameObjects.add(gameObject);
    }

    public boolean removeGameObject(GameObject gameObject){
        return gameObjects.remove(gameObject);
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
    public void addKeyListener(KeyListener listener){
        globalKeyListener.addListener(listener);
    }

    public void removeKeyListener(KeyListener listener){
        globalKeyListener.removeListener(listener);
    }
    //endregion

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(thread != null){
            long time = System.currentTimeMillis();

            frame.repaint();

            callUpdatables();

            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0)remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            deltaTime = (float) (System.currentTimeMillis() - time)/1000;
        }
    }

}
