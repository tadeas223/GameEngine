import engine.Engine;
import generic.Player;

public class Example {
    public static void main(String[] args) {
        Engine engine = Engine.getInstance();
        Player p = new Player();
        p.setPosition(50,50);
        engine.addGameObject(p);

        Player p2 = new Player(150);
        p2.setLayer(0);

        engine.addGameObject(p2);
    }
}
