package gameObject;

import engine.Engine;
import engine.Updatable;
import gameObject.GameObject;

public abstract class Module implements Updatable {
    protected GameObject source;
    protected Engine engine;
    public Module(GameObject source){
        this.engine = Engine.getInstance();
        this.source = source;
    }

    public GameObject getSource() {
        return source;
    }
}
