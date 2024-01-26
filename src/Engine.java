
public class Engine implements Runnable{
    public static final int FPS = 60;
    private static Engine instance = null;
    private Thread thread = null;
    private GameFrame frame = new GameFrame();
    private Engine(){
        frame.setVisible(true);
        thread = new Thread(this);
        thread.start();
    }

    public static Engine getInstance(){
        if(instance == null) instance = new Engine();
        return instance;
    }

    public static Engine resetInstance(){
        instance = null;
        return instance;
    }

    public void setTitle(String title){
        frame.setTitle(title);
    }

    public boolean addGameObject(GameObject gameObject){
        return frame.addGameObject(gameObject);
    }

    public boolean removeGameObject(GameObject gameObject){
        return frame.removeGameObject(gameObject);
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(thread != null){
            frame.repaint();

            System.out.println("update");
            frame.callUpdatables();

            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0)remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
