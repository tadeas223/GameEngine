import engine.Engine;
import generic.Player;

public class Example {
    public static void main(String[] args) {
        Engine engine = Engine.getInstance();
        Player p = new Player();
        p.setPosition(50,50);
        engine.addGameObject(p);
        engine.addGameObject(new Player(150));

    }
}
