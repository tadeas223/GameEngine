import engine.Engine;
import test.Player;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Engine engine = Engine.getInstance();
        engine.addGameObject(new Player());
    }
}