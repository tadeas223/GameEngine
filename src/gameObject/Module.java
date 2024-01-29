package gameObject;

import engine.Updatable;

/**
 * This class is used along with {@link GameObject}.
 * This class should be used for adding functionalities for the {@linkplain GameObject}.
 *
 * @see GameObject
 */
public abstract class Module implements Updatable {
    protected GameObject source;
    private boolean active = true;

    public abstract void start();
    public GameObject getSource() {
        return source;
    }

    public void setSource(GameObject source) {
        this.source = source;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
