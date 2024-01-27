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

    public GameObject getSource() {
        return source;
    }
}
